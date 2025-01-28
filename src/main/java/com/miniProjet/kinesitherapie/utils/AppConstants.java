package com.miniProjet.kinesitherapie.utils;
/**
 * Had l-class wa7ed repository dyal constants lli kaynin fl-app.
 * Kaynin constants dyal pagination defaults w error messages lli kitskhdmo fl-app.
 *
 * <h4>T3limat bach tzid constants jdad:</h4>
 * <ul>
 *     <li>St3mel smiyat lli mafhomin lkola constant.</li>
 *     <li>Groupi lconstants lli 3andhoum 3ala9a m3a ba3dyathoum, b7al constants dyal pagination w error messages, m3a b3diyat-hom.</li>
 *     <li>7at ghi lconstants li aytsta3mlo fl-app kamla. Ila kan constants khass ghi b-module wa7ed, madiroush hna.</li>
 * </ul>
 */
public class AppConstants {

    //Pagination
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_DIR = "asc";
    public static final String SORT_BY = "nom";

    //Error messages
    public static final String INVALID_PHONE_NUMBER = "Phone number must start with 06, 07, or 05 and contain exactly 10 digits";
    public static final String INVALID_EMAIL = "Invalid email address";
    public static final String INVALID_NOM = "Nom can't be empty";
    public static final String INVALID_PRENOM = "Prenom can't be empty";
    public static final String INVALID_ADRESSE = "Adresse can't be empty";
}
