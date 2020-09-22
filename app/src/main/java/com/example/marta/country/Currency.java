/*
 *   Copyright (c) 2020 Mayasoft LLC, UZ
 *         https://marta.uz
 *
 *        All rights reserved
 */

package com.example.marta.country;


import androidx.room.TypeConverter;

import lombok.Getter;

@Getter
public enum Currency {
    ALL(8, "Lek"),
    AED(784, "UAE Dirham"),
    AFN(971, "Afghani"),
    AMD(51, "Armenian Dram"),
    ANG(532, "Netherlands Antillean Guilder"),
    AOA(973, "Kwanza"),
    ARS(32, "Argentine Peso"),
    AUD(36, "Australian Dollar"),
    AWG(533, "Aruban Florin"),
    AZN(944, "Azerbaijan Manat"),
    BAM(977, "Convertible Mark"),
    BBD(52, "Barbados Dollar"),
    BDT(50, "Taka"),
    BGN(975, "Bulgarian Lev"),
    BHD(48, "Bahraini Dinar"),
    BIF(108, "Burundi Franc"),
    BMD(60, "Bermudian Dollar"),
    BND(96, "Brunei Dollar"),
    BOB(68, "Boliviano"),
    BRL(986, "Brazilian Real"),
    BSD(44, "Bahamian Dollar"),
    BWP(72, "Pula"),
    BYN(933, "Belarusian Ruble"),
    BZD(84, "Belize Dollar"),
    CAD(124, "Canadian Dollar"),
    CDF(976, "Congolese Franc"),
    CHF(756, "Swiss Franc"),
    CLP(152, "Chilean Peso"),
    CNY(156, "Yuan Renminbi"),
    COP(170, "Colombian Peso"),
    CRC(188, "Costa Rican Colon"),
    CUC(931, "Peso Convertible"),
    CVE(132, "Cabo Verde Escudo"),
    CZK(203, "Czech Koruna"),
    DJF(262, "Djibouti Franc"),
    DKK(208, "Danish Krone"),
    DOP(214, "Dominican Peso"),
    DZD(12, "Algerian Dinar"),
    EGP(818, "Egyptian Pound"),
    ERN(232, "Nakfa"),
    ETB(230, "Ethiopian Birr"),
    EUR(978, "Euro"),
    FJD(242, "Fiji Dollar"),
    GBP(826, "Pound Sterling"),
    GEL(981, "Lari"),
    GHS(936, "Ghana Cedi"),
    GIP(292, "Gibraltar Pound"),
    GMD(270, "Dalasi"),
    GNF(324, "Guinean Franc"),
    GTQ(320, "Quetzal"),
    GYD(328, "Guyana Dollar"),
    HKD(344, "Hong Kong Dollar"),
    HNL(340, "Lempira"),
    HRK(191, "Kuna"),
    HUF(348, "Forint"),
    IDR(360, "Rupiah"),
    ILS(376, "New Israeli Sheqel"),
    INR(356, "Indian Rupee"),
    IQD(368, "Iraqi Dinar"),
    IRR(364, "Iranian Rial"),
    ISK(352, "Iceland Krona"),
    JMD(388, "Jamaican Dollar"),
    JOD(400, "Jordanian Dinar"),
    JPY(392, "Yen"),
    KES(404, "Kenyan Shilling"),
    KGS(417, "Som"),
    KHR(116, "Riel"),
    KMF(174, "Comorian Franc"),
    KPW(408, "North Korean Won"),
    KRW(410, "Won"),
    KWD(414, "Kuwaiti Dinar"),
    KYD(136, "Cayman Islands Dollar"),
    KZT(398, "Tenge"),
    LAK(418, "Lao Kip"),
    LBP(422, "Lebanese Pound"),
    LKR(144, "Sri Lanka Rupee"),
    LRD(430, "Liberian Dollar"),
    LYD(434, "Libyan Dinar"),
    MAD(504, "Moroccan Dirham"),
    MDL(498, "Moldovan Leu"),
    MGA(969, "Malagasy Ariary"),
    MKD(807, "Denar"),
    MMK(104, "Kyat"),
    MNT(496, "Tugrik"),
    MOP(446, "Pataca"),
    MRO(478, "Ouguiya"),
    MUR(480, "Mauritius Rupee"),
    MVR(462, "Rufiyaa"),
    MWK(454, "Malawi Kwacha"),
    MXN(484, "Mexican Peso"),
    MYR(458, "Malaysian Ringgit"),
    MZN(943, "Mozambique Metical"),
    NAD(516, "Namibia Dollar,Rand"),
    NGN(566, "Naira"),
    NIO(558, "Cordoba Oro"),
    NOK(578, "Norwegian Krone"),
    NPR(524, "Nepalese Rupee"),
    NZD(554, "New Zealand Dollar"),
    OMR(512, "Rial Omani"),
    PEN(604, "Sol"),
    PGK(598, "Kina"),
    PHP(608, "Philippine Piso"),
    PKR(586, "Pakistan Rupee"),
    PLN(985, "Zloty"),
    PYG(600, "Guarani"),
    QAR(634, "Qatari Rial"),
    RON(946, "Romanian Leu"),
    RSD(941, "Serbian Dinar"),
    RUB(643, "Russian Ruble"),
    RWF(646, "Rwanda Franc"),
    SAR(682, "Saudi Riyal"),
    SBD(90, "Solomon Islands Dollar"),
    SCR(690, "Seychelles Rupee"),
    SDG(938, "Sudanese Pound"),
    SEK(752, "Swedish Krona"),
    SGD(702, "Singapore Dollar"),
    SHP(654, "Saint Helena Pound"),
    SLL(694, "Leone"),
    SOS(706, "Somali Shilling"),
    SRD(968, "Surinam Dollar"),
    SSP(728, "South Sudanese Pound"),
    STD(678, "Dobra"),
    SYP(760, "Syrian Pound"),
    SZL(748, "Lilangeni"),
    THB(764, "Baht"),
    TJS(972, "Somoni"),
    TMT(934, "Turkmenistan New Manat"),
    TND(788, "Tunisian Dinar"),
    TOP(776, "Pa’anga"),
    TRY(949, "Turkish Lira"),
    TTD(780, "Trinidad and Tobago Dollar"),
    TZS(834, "Tanzanian Shilling"),
    UAH(980, "Hryvnia"),
    UGX(800, "Uganda Shilling"),
    USD(840, "US Dollar"),
    UYU(858, "Peso Uruguayo"),
    UZS(860, "Uzbekistan Sum"),
    VEF(937, "Bolívar"),
    VND(704, "Dong"),
    VUV(548, "Vatu"),
    WST(882, "Tala"),
    XAF(950, "CFA Franc BEAC"),
    XCD(951, "East Caribbean Dollar"),
    XOF(952, "CFA Franc BCEAO"),
    XPF(953, "CFP Franc"),
    YER(886, "Yemeni Rial"),
    ZAR(710, "Rand"),
    ZMW(967, "Zambian Kwacha"),
    ZWL(932, "Zimbabwe Dollar");

    private int code;
    private String name;
    private static Currency[] multiDigitsCurrencySet = new Currency[] {UZS};

    Currency(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static boolean hasDecimals(Currency currency) {
        for (Currency c : multiDigitsCurrencySet) if (currency.equals(c)) return false;
        return true;
    }

    public String code() {
        return String.format("%04d", code);
    }

    public static Currency findByCode(String codeTxt) {
        int code = Integer.valueOf(codeTxt);
        for (Currency currency : Currency.values()) {
            if (currency.code == code) return currency;
        }
        throw new IllegalArgumentException();
    }

    @TypeConverter
    public static Currency toCurrency(String name) {
        return Currency.valueOf(name);
    }

    @TypeConverter
    public static String fromCurrency(Currency currency) {
        return currency.name();
    }
}
