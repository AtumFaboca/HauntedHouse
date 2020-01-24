package HauntedHouse;

import Exceptions.VertexNotFoundException;
import Graph.WeightGraphADT;
import LinkedList.ArrayUnorderedList;

public interface HauntedHouseGraphADT<T> extends WeightGraphADT<T> {

    /**
     * Change the position of the player to the defined position.
     *
     * @param nextPosition new position of the player
     * @return if the the transaction was a sucess
     * @throws VertexNotFoundException if the vertex target isnt found
     */
    public boolean changePosition(T nextPosition) throws VertexNotFoundException;

    /**
     * Returns the current position of the player.
     *
     * @return the current position of the player
     */
    public T getCurrentPosition();
    
    /**
     * 
     * @param vertex actual position of the player
     * @return list of locations connected to actual position
     * @throws VertexNotFoundException if the vertex target isnt found
     */
    public ArrayUnorderedList<T> getAvailableDoors(T vertex) throws VertexNotFoundException;

    /**
     * Checks if the actual position of the player is the outside, wich means
     * that the player completed is the game.
     *
     * @return if the player completed the game or not
     */
    public boolean isComplete();

    /**
     * Checks if the player still has health points.
     *
     * @return if the player health points if bigger than 0
     */
    public boolean isAlive();

    /**
     * Method responsible for changing the amount of HP lost when the player
     * enters a room with a ghost, depending on the level of difficulty.
     *
     */
    public void setDifficulty();

    /**
     * Sets in wich vertex the player starts the game.
     *
     * @param vertex where the player starts.
     */
    public void setStartPosition(T vertex);

    /**
     * Sets in wich vrtex the player ends the game.
     *
     * @param vertex where the player ends.
     */
    public void setEndPosition(T vertex);
}
