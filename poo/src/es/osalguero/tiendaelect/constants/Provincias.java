package es.osalguero.tiendaelect.constants;

public enum Provincias
{
    ALAVA("Álava"), 
    ALBACETE("Albacete"), 
    ALICANTE("Alicante"), 
    ALMERIA("Almería"), 
    ASTURIAS("Asturias"), 
    AVILA("Ávila"), 
    BADAJOZ("Badajoz"), 
    BARCELONA("Barcelona"), 
    BURGOS("Burgos"), 
    CACERES("Cáceres"), 
    CADIZ("Cádiz"), 
    CANTABRIA("Cantabria"), 
    CASTELLON("Castellón"), 
    CREAL("Ciudad Real"), 
    CORDOBA("Córdoba"), 
    CORUNA("La Coruña"), 
    CUENCA("Cuenca"), 
    GERONA("Gerona"), 
    GRANADA("Granada"), 
    GUADALAJARA("Guadalajara"), 
    GUIPUZCOA("Guipúzcoa"), 
    HUELVA("Huelva"), 
    HUESCA("Huesca"), 
    BALEARES("Islas Baleares"), 
    JAEN("Jaén"), 
    LEON("León"), 
    LERIDA("Lérida"), 
    LUGO("Lugo"), 
    MADRID("Madrid"), 
    MALAGA("Málaga"), 
    MURCIA("Murcia"), 
    NAVARRA("Navarra"), 
    ORENSE("Orense"), 
    PALENCIA("Palencia"), 
    PALMAS("Las Palmas"), 
    PONTEVEDRA("Pontevedra"), 
    RIOJA("La Rioja"), 
    SALAMANCA("Salamanca"), 
    SEGOVIA("Segovia"), 
    SEVILLA("Sevilla"), 
    SORIA("Soria"), 
    TARRAGONA("Tarragona"), 
    TENERIFE("Santa Cruz de Tenerife"), 
    TERUEL("Teruel"), 
    TOLEDO("Toledo"), 
    VALENCIA("Valencia"), 
    VALLADOLID("Valladolid"), 
    VIZCAYA("Vizcaya"), 
    ZAMORA("Zamora"), 
    ZARAGOZA("Zaragoza");
    
    private String nombre;
    
    private Provincias(final String nombre) {
        this.nombre = nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
}
