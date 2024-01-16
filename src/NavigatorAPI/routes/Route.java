package NavigatorAPI.routes;

import java.util.List;
import java.util.Objects;

public class Route implements Comparable<Route> {
    private String id;
    private double distance;
    private int popularity;
    private boolean isFavorite;
    private List<String> locationPoints;

    public Route() {
    }

    public Route(String id, double distance, int popularity, boolean isFavorite, List<String> locationPoints)  {
        this.id = id;
        this.distance = distance;
        this.popularity = popularity;
        this.isFavorite = isFavorite;
        this.locationPoints = locationPoints;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getPopularity() {
        return popularity;
    }

    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    public List<String> getLocationPoints() {
        return locationPoints;
    }

    public void setLocationPoints(List<String> locationPoints) {
        this.locationPoints = locationPoints;
    }

    @Override
    public int compareTo(Route otherRoute) {
        if (this == otherRoute) return 0;

        // Compare by distance
        int distanceComparison = Double.compare(this.distance, otherRoute.distance);
        if (distanceComparison != 0) return distanceComparison;

        // Compare by locationPoints
        int locationComparison = this.locationPoints.get(0).compareTo(otherRoute.locationPoints.get(0));
        if (locationComparison != 0) return locationComparison;
        locationComparison = this.locationPoints.get(this.locationPoints.size() -1).compareTo(otherRoute.locationPoints.get(otherRoute.locationPoints.size() -1));
        if (locationComparison != 0) return locationComparison;

        // Compare by id
        return Objects.compare(this.id, otherRoute.id, String::compareTo);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;
        if (Objects.equals(id,route.id)) return true;
        if (Double.compare(route.distance, distance) != 0) return false;
        if (popularity != route.popularity) return false;
        if (isFavorite != route.isFavorite) return false;
        if (!Objects.equals(id, route.id)) return false;
        return Objects.equals(locationPoints, route.locationPoints);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        temp = Double.doubleToLongBits(distance);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + popularity;
        result = 31 * result + (isFavorite ? 1 : 0);
        result = 31 * result + (locationPoints != null ? locationPoints.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Route{" +
                "id='" + id + '\'' +
                ", distance=" + distance +
                ", popularity=" + popularity +
                ", isFavorite=" + isFavorite +
                ", locationPoints=" + locationPoints +
                '}';
    }
}
