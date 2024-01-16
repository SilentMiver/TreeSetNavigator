package NavigatorAPI.navigator;

import NavigatorAPI.routes.Route;
import NavigatorAPI.utils.Set.TreeSet;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NavigatorImpl implements Navigator {
    TreeSet<Route> routeTreeSet = new TreeSet<>();

    @Override
    public void addRoute(Route route) {
        routeTreeSet.add(route);
    }

    @Override
    public void removeRoute(String routeId) {
        for (var r:routeTreeSet){
            if (r.getId().equals(routeId)){
                routeTreeSet.remove(r);
                break;
            }
        }

    }

    @Override
    public boolean contains(Route route) {
        return routeTreeSet.contains(route);
    }

    @Override
    public int size() {
        return routeTreeSet.size();
    }

    @Override
    public Route getRoute(String routeId) {
        for (var r:routeTreeSet){
            if (r.getId().equals(routeId)){
                return r;
            }
        }
         return null;

    }

    @Override
    public void chooseRoute(String routeId) {
        for (var route : routeTreeSet){
            if (route.getId().equals(routeId)){
                route.setPopularity(route.getPopularity() + 1);
            }
        }
    }

    @Override
    public Iterable<Route> searchRoutes(String startPoint, String endPoint) {
        List<Route> result = new ArrayList<>();

        for (Route route : routeTreeSet) {
            List<String> points = route.getLocationPoints();
            if (points.size() >= 2 && points.contains(startPoint) && points.contains(endPoint)) {
                result.add(route);
            }
        }

        result.sort(Comparator.comparing(Route::isFavorite)
                .thenComparing(route -> {
                    int startPointIndex = route.getLocationPoints().indexOf(startPoint);
                    int endPointIndex = route.getLocationPoints().indexOf(endPoint);
                    if (endPointIndex >= startPointIndex) {
                        return endPointIndex - startPointIndex;
                    } else {
                        return startPointIndex - endPointIndex;
                    }
                }).reversed()
                .thenComparing(Route::getPopularity).reversed());
        return result;
    }

    @Override
    public Iterable<Route> getFavoriteRoutes(String destinationPoint) {
        List<Route> result = new ArrayList<>();

        for (Route route : routeTreeSet) {
            List<String> points = route.getLocationPoints();
            if (route.isFavorite() && points.size() >= 2 && !points.get(0).equals(destinationPoint)) {
                result.add(route);
            }
        }
        result.sort(Comparator.comparing(Route::getDistance).thenComparing(Route::getPopularity));
        return result;
    }

    @Override
    public Iterable<Route> getTop3Routes() {
        List<Route> allRoutes = new ArrayList<>();
        for(var r : routeTreeSet) allRoutes.add(r);
        allRoutes.sort(Comparator.comparing(Route::getPopularity).reversed()
                .thenComparing(Route::getDistance)
                .thenComparing(route -> route.getLocationPoints().size()));

            // Тут можно поменять top3 на top5
        return allRoutes.subList(0, Math.min(3, allRoutes.size()));
    }
}
