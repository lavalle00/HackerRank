import java.util.*;

public class Solution {
    private int _numCities, _numRoads = -1;
    private long _libCost, _roadCost = -1;
    public int totalCost = 0;
    /* Prompt
        For each query, find the minimum cost of making libraries
        accessible to all the citizens and print it on a new line.
     */
    /*  Notes/ Thought Processes
        Queries are a HashMap of nodes

        Cities:
            have a library flag,
            contain vector of cities it can connect to.

     */
    /* Edge Cases:

       if repairing is > building library, build lib in each city.

       m can equal 0 -> 0 edges... ?
     */

    public static void main(String[] args) {
        Solution sol = new Solution();
        final String _sampleInput =
                "2\n" +       // 2 queries
                "3 3 2 1\n" + // 3 cities, 3 roads, $2 build library, $1 repair road
                "1 2\n" +     //
                "3 1\n" +
                "2 3\n" +
                "6 6 2 5\n" + // 6 cities, 6 roads, $2 library, $1 repair
                "1 3\n" +
                "3 4\n" +
                "2 4\n" +
                "1 2\n" +
                "2 3\n" +
                "5 6";
        Scanner in = new Scanner(_sampleInput);
        sol.init(in);

    }
    public void init(Scanner in) {
        ArrayList<City> _arrCity = null;
        int _numQueries = in.nextInt();
        for(int i = 0; i < _numQueries; i++){
            _arrCity = new ArrayList<>();
            System.out.println("");
            this._numCities = in.nextInt();
            this._numRoads = in.nextInt();
            this._libCost = in.nextLong();
            this._roadCost = in.nextLong();
            for(int j = 0; j < _numRoads; j++){
                Integer city1 = in.nextInt();
                Integer city2 = in.nextInt();
                City temp1 = new City(city1);
                City temp2 = new City(city2);
                temp1.addToNeighbors(temp2);
                temp2.addToNeighbors(temp1);

                System.out.printf("Edge:\t%d\t->\t%d\n", temp1.id, temp2.id);
            }

            whereToLibrary(_arrCity);
        }
    }
    private int whereToLibrary(ArrayList<City> _arrCity) {
        System.out.printf("\nCities: %d\nRoads: %d\nLibrary Cost: %d\nRoad Cost: %d\n", _numCities, _numRoads, _libCost, _roadCost);
        /* Cover edge cases first*/

        /* cost to repair road >= cost to build library*/
        if(_numRoads == 0) {
            totalCost = (int)_libCost * _numCities;
        }
        if(_roadCost >= _libCost) {
            totalCost = (int)_libCost * _numCities;
        }
        return 0;
    }
    class City {
        public City(Integer id) {
            this.id = id;
            neighbors = new ArrayList<>();
        }

        public boolean isLibrary() {
            return isLibrary;
        }

        public void setLibrary(boolean library) {
            isLibrary = library;
        }

        public ArrayList<City> getNeighbors() {
            return neighbors;
        }

        public void setNeighbors(ArrayList<City> neighbors) {
            this.neighbors = neighbors;
        }

        private boolean isLibrary = false;
        private Integer id;
        private ArrayList<City> neighbors;

        public void addToNeighbors(City neighbor) {
//            System.out.println("Adding:\t" + neighbor + "  to:\t" + this.toString() + "'s neighbor list");
            neighbors.add(neighbor);
        }
        @Override
        public String toString() {
            return this.id.toString();
        }
    }
}