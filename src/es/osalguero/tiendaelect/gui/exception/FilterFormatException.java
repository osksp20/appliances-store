package es.osalguero.tiendaelect.gui.exception;

public class FilterFormatException extends Exception
{
    private static final long serialVersionUID = 2642067887521294438L;
    
    protected FilterFormatException() {
    }
    
    public FilterFormatException(final String message) {
        super(message);
    }
}
