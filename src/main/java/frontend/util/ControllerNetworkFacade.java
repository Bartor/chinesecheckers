package frontend.util;

import frontend.networking.MessageInterpreter;

/***
 * Makes communication between controllers and network a bit easier.
 * MessageInterpreter has a public static method interpret, so that's why we don't really need to take it as an arg.
 */
public class ControllerNetworkFacade {
    /***
     * Tells server that we moved.
     * @param oldPos Position that we moved from, [x, y].
     * @param newPos Position that we moved to, [x, y].
     */
    public void moved(int[] oldPos, int[] newPos) {

    }
}
