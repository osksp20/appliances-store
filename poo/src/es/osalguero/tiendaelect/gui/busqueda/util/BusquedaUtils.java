package es.osalguero.tiendaelect.gui.busqueda.util;

public class BusquedaUtils
{
    public static boolean compareStrings(final String compare1, final String compare2) {
        return compare1 == null || (compare2 != null && compare1.trim().toLowerCase().equals(compare2.trim().toLowerCase()));
    }
}
