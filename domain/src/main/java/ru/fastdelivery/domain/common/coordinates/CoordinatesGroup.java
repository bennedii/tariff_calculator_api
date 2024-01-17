package ru.fastdelivery.domain.common.coordinates;

import java.util.List;

public record CoordinatesGroup(
        Coordinates destination,
        Coordinates departure
) {

    public static CoordinatesGroup getCoordinatesGroup(List<Coordinates> coordinates) {
        return new CoordinatesGroup(coordinates.get(0), coordinates.get(1));
    }

    public double calculateDistance(){
        var curDep = this.departure();
        var curDes = this.destination();

        double lat1 = curDep.latitude(), lon1 = curDep.longitude(), lat2 = curDes.latitude(),
        lon2 = curDes.longitude();

        double R = 6371;

        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return  (double) (Math.round(R * c * 100)) / 100 ;
    }
}
