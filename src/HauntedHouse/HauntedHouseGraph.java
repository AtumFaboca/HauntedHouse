package HauntedHouse;

import Exceptions.VertexNotFoundException;
import Graph.WeightDirectedMatrixGraph;
import LinkedList.ArrayUnorderedList;
import java.io.FileNotFoundException;

public class HauntedHouseGraph<T> extends WeightDirectedMatrixGraph<T> implements HauntedHouseGraphADT<T> {

    private String playerName;
    private String mapName;
    private double healthPoints;
    private int level;
    private T endPosition;
    private T position;
    private final ArrayUnorderedList<T> pathTaken;
    private ClassificationManager<T> classification;

    public HauntedHouseGraph() {
        super();
        this.playerName = null;
        this.pathTaken = new ArrayUnorderedList<>();
    }

    /**
     * Change the position of the playerName to the defined position.
     *
     * @param nextPosition new position of the playerName
     * @return if the the transaction was a sucess
     * @throws VertexNotFoundException if the vertex target isnt found
     */
    @Override
    public boolean changePosition(T nextPosition) throws VertexNotFoundException {
        int positionIndex = this.getIndex(this.position);
        int nextPositionIndex = this.getIndex(nextPosition);

        if (this.indexIsValid(nextPositionIndex)) {
            if (this.adjMatrix[positionIndex][nextPositionIndex] >= 0) {
                this.position = nextPosition;
                this.pathTaken.addToRear(this.position);
                this.healthPoints = this.healthPoints - this.adjMatrix[positionIndex][nextPositionIndex];
                return true;
            } else {
                return false;
            }
        } else {
            throw new VertexNotFoundException();
        }
    }

    /**
     * Returns the current position of the playerName.
     *
     * @return the current position of the playerName
     */
    @Override
    public T getCurrentPosition() {
        return this.position;
    }

    /**
     *
     * @param vertex actual position of the player
     * @return list of locations connected to actual position
     * @throws VertexNotFoundException if the vertex target isnt found
     */
    @Override
    public ArrayUnorderedList<T> getAvailableDoors(T vertex) throws VertexNotFoundException {
        ArrayUnorderedList<T> options = new ArrayUnorderedList<>();
        int vertexIndex = this.getIndex(vertex);

        if (this.indexIsValid(vertexIndex)) {
            for (int i = 0; i < this.adjMatrix.length - 1; i++) {
                if (this.adjMatrix[vertexIndex][i] >= 0) {
                    options.addToRear(this.vertices[i]);
                }
            }
        } else {
            throw new VertexNotFoundException();
        }
        return options;
    }

    /**
     * Checks if the actual position of the playerName is the outside, wich
     * means that the playerName completed is the game.
     *
     * @return if the playerName completed the game or not
     */
    @Override
    public boolean isComplete() {
        return this.position == this.endPosition;
    }

    /**
     * Checks if the playerName still has health points.
     *
     * @return if the playerName health points if bigger than 0
     */
    @Override
    public boolean isAlive() {
        return this.healthPoints > 0;
    }

    /**
     * Method responsible for changing the amount of HP lost when the player
     * enters a room with a ghost, depending on the level of difficulty.
     *
     */
    @Override
    public void setDifficulty() {
        for (int i = 0; i < this.numVertices; i++) {
            for (int j = 0; j < this.numVertices; j++) {
                if (this.adjMatrix[i][j] > 0) {
                    this.adjMatrix[i][j] = this.adjMatrix[i][j] * this.level;
                }
            }
        }
    }

    /**
     * Sets in wich vertex the player starts the game.
     *
     * @param vertex where the player starts.
     */
    @Override
    public void setStartPosition(T vertex) {
        this.position = vertex;
        this.pathTaken.addToRear(this.position);
    }

    /**
     * Sets in wich vrtex the player ends the game.
     *
     * @param vertex where the player ends.
     */
    @Override
    public void setEndPosition(T vertex) {
        this.endPosition = vertex;
    }

    /**
     * Method responsible for adding a new classification to the database.
     */
    @Override
    public void addNewClassification() {
        if (this.playerName != null) {
            this.classification = new ClassificationManager<>();
            this.classification.addNewClassification(this.playerName, this.mapName, this.pathTaken, this.healthPoints);
        }
    }

    /**
     * Returns the classification table.
     *
     * @return classification table
     * @throws FileNotFoundException if the classifications file is not found
     */
    @Override
    public String getClassificationTable() throws FileNotFoundException {
        this.classification = new ClassificationManager<>();
        return this.classification.getClassificationTable();
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public String getMapName() {
        return this.mapName;
    }

    public void setPlayerName(String name) {
        this.playerName = name;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    public void setHealthPoints(double healthPoints) {
        this.healthPoints = healthPoints;
    }

    public double getHealthPoints() {
        return this.healthPoints;
    }

    public void setLevel(int level) {
        this.level = level;
    }

}