package mb.dabm.servcatapi.helper;

public class Helper {

    /**
     * Responsável por VRF se a String eh numérica
     *
     * @param str
     * @return boolean
     */
    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

}
