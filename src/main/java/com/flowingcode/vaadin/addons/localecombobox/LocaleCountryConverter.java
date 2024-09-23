/*-
 * #%L
 * LocaleComboBox Add-on
 * %%
 * Copyright (C) 2024 Flowing Code
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
package com.flowingcode.vaadin.addons.localecombobox;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for converting between different formats of country codes.
 *
 * <p>The {@code LocaleCountryConverter} class provides methods to convert country codes from ISO
 * 3166-1 alpha-2, alpha-3, and numeric-3 formats to the 3166-1 alpha-2 format. The class uses
 * static methods, so no instances are needed.
 *
 * <p>
 * Example usage:
 * 
 * <pre>
 * String alpha2Code = LocaleCountryConverter.convertToISO3166Code("ARG").orElse("Unknown");
 * </pre>
 *
 * @see #convertToISO3166Code(String)
 */
public class LocaleCountryConverter {

  private static final Map<String, String> conversions = new HashMap<>();

  private static void add(String alpha2, String alpha3, int numeric) {
    conversions.put(alpha2, alpha2);
    conversions.put(alpha3, alpha2);
    conversions.put(Integer.toString(numeric), alpha2);
  }

  static {
    add("AF", "AFG", 4);
    add("AL", "ALB", 8);
    add("DZ", "DZA", 12);
    add("AS", "ASM", 16);
    add("AD", "AND", 20);
    add("AO", "AGO", 24);
    add("AI", "AIA", 660);
    add("AQ", "ATA", 10);
    add("AG", "ATG", 28);
    add("AR", "ARG", 32);
    add("AM", "ARM", 51);
    add("AW", "ABW", 533);
    add("AU", "AUS", 36);
    add("AT", "AUT", 40);
    add("AZ", "AZE", 31);
    add("BS", "BHS", 44);
    add("BH", "BHR", 48);
    add("BD", "BGD", 50);
    add("BB", "BRB", 52);
    add("BY", "BLR", 112);
    add("BE", "BEL", 56);
    add("BZ", "BLZ", 84);
    add("BJ", "BEN", 204);
    add("BM", "BMU", 60);
    add("BT", "BTN", 64);
    add("BO", "BOL", 68);
    add("BQ", "BES", 535);
    add("BA", "BIH", 70);
    add("BW", "BWA", 72);
    add("BV", "BVT", 74);
    add("BR", "BRA", 76);
    add("IO", "IOT", 86);
    add("BN", "BRN", 96);
    add("BG", "BGR", 100);
    add("BF", "BFA", 854);
    add("BI", "BDI", 108);
    add("CV", "CPV", 132);
    add("KH", "KHM", 116);
    add("CM", "CMR", 120);
    add("CA", "CAN", 124);
    add("KY", "CYM", 136);
    add("CF", "CAF", 140);
    add("TD", "TCD", 148);
    add("CL", "CHL", 152);
    add("CN", "CHN", 156);
    add("CX", "CXR", 162);
    add("CC", "CCK", 166);
    add("CO", "COL", 170);
    add("KM", "COM", 174);
    add("CD", "COD", 180);
    add("CG", "COG", 178);
    add("CK", "COK", 184);
    add("CR", "CRI", 188);
    add("HR", "HRV", 191);
    add("CU", "CUB", 192);
    add("CW", "CUW", 531);
    add("CY", "CYP", 196);
    add("CZ", "CZE", 203);
    add("CI", "CIV", 384);
    add("DK", "DNK", 208);
    add("DJ", "DJI", 262);
    add("DM", "DMA", 212);
    add("DO", "DOM", 214);
    add("EC", "ECU", 218);
    add("EG", "EGY", 818);
    add("SV", "SLV", 222);
    add("GQ", "GNQ", 226);
    add("ER", "ERI", 232);
    add("EE", "EST", 233);
    add("SZ", "SWZ", 748);
    add("ET", "ETH", 231);
    add("FK", "FLK", 238);
    add("FO", "FRO", 234);
    add("FJ", "FJI", 242);
    add("FI", "FIN", 246);
    add("FR", "FRA", 250);
    add("GF", "GUF", 254);
    add("PF", "PYF", 258);
    add("TF", "ATF", 260);
    add("GA", "GAB", 266);
    add("GM", "GMB", 270);
    add("GE", "GEO", 268);
    add("DE", "DEU", 276);
    add("GH", "GHA", 288);
    add("GI", "GIB", 292);
    add("GR", "GRC", 300);
    add("GL", "GRL", 304);
    add("GD", "GRD", 308);
    add("GP", "GLP", 312);
    add("GU", "GUM", 316);
    add("GT", "GTM", 320);
    add("GG", "GGY", 831);
    add("GN", "GIN", 324);
    add("GW", "GNB", 624);
    add("GY", "GUY", 328);
    add("HT", "HTI", 332);
    add("HM", "HMD", 334);
    add("VA", "VAT", 336);
    add("HN", "HND", 340);
    add("HK", "HKG", 344);
    add("HU", "HUN", 348);
    add("IS", "ISL", 352);
    add("IN", "IND", 356);
    add("ID", "IDN", 360);
    add("IR", "IRN", 364);
    add("IQ", "IRQ", 368);
    add("IE", "IRL", 372);
    add("IM", "IMN", 833);
    add("IL", "ISR", 376);
    add("IT", "ITA", 380);
    add("JM", "JAM", 388);
    add("JP", "JPN", 392);
    add("JE", "JEY", 832);
    add("JO", "JOR", 400);
    add("KZ", "KAZ", 398);
    add("KE", "KEN", 404);
    add("KI", "KIR", 296);
    add("KP", "PRK", 408);
    add("KR", "KOR", 410);
    add("KW", "KWT", 414);
    add("KG", "KGZ", 417);
    add("LA", "LAO", 418);
    add("LV", "LVA", 428);
    add("LB", "LBN", 422);
    add("LS", "LSO", 426);
    add("LR", "LBR", 430);
    add("LY", "LBY", 434);
    add("LI", "LIE", 438);
    add("LT", "LTU", 440);
    add("LU", "LUX", 442);
    add("MO", "MAC", 446);
    add("MG", "MDG", 450);
    add("MW", "MWI", 454);
    add("MY", "MYS", 458);
    add("MV", "MDV", 462);
    add("ML", "MLI", 466);
    add("MT", "MLT", 470);
    add("MH", "MHL", 584);
    add("MQ", "MTQ", 474);
    add("MR", "MRT", 478);
    add("MU", "MUS", 480);
    add("YT", "MYT", 175);
    add("MX", "MEX", 484);
    add("FM", "FSM", 583);
    add("MD", "MDA", 498);
    add("MC", "MCO", 492);
    add("MN", "MNG", 496);
    add("ME", "MNE", 499);
    add("MS", "MSR", 500);
    add("MA", "MAR", 504);
    add("MZ", "MOZ", 508);
    add("MM", "MMR", 104);
    add("NA", "NAM", 516);
    add("NR", "NRU", 520);
    add("NP", "NPL", 524);
    add("NL", "NLD", 528);
    add("NC", "NCL", 540);
    add("NZ", "NZL", 554);
    add("NI", "NIC", 558);
    add("NE", "NER", 562);
    add("NG", "NGA", 566);
    add("NU", "NIU", 570);
    add("NF", "NFK", 574);
    add("MK", "MKD", 807);
    add("MP", "MNP", 580);
    add("NO", "NOR", 578);
    add("OM", "OMN", 512);
    add("PK", "PAK", 586);
    add("PW", "PLW", 585);
    add("PS", "PSE", 275);
    add("PA", "PAN", 591);
    add("PG", "PNG", 598);
    add("PY", "PRY", 600);
    add("PE", "PER", 604);
    add("PH", "PHL", 608);
    add("PN", "PCN", 612);
    add("PL", "POL", 616);
    add("PT", "PRT", 620);
    add("PR", "PRI", 630);
    add("QA", "QAT", 634);
    add("RO", "ROU", 642);
    add("RU", "RUS", 643);
    add("RW", "RWA", 646);
    add("RE", "REU", 638);
    add("BL", "BLM", 652);
    add("SH", "SHN", 654);
    add("KN", "KNA", 659);
    add("LC", "LCA", 662);
    add("MF", "MAF", 663);
    add("PM", "SPM", 666);
    add("VC", "VCT", 670);
    add("WS", "WSM", 882);
    add("SM", "SMR", 674);
    add("ST", "STP", 678);
    add("SA", "SAU", 682);
    add("SN", "SEN", 686);
    add("RS", "SRB", 688);
    add("SC", "SYC", 690);
    add("SL", "SLE", 694);
    add("SG", "SGP", 702);
    add("SX", "SXM", 534);
    add("SK", "SVK", 703);
    add("SI", "SVN", 705);
    add("SB", "SLB", 90);
    add("SO", "SOM", 706);
    add("ZA", "ZAF", 710);
    add("GS", "SGS", 239);
    add("SS", "SSD", 728);
    add("ES", "ESP", 724);
    add("LK", "LKA", 144);
    add("SD", "SDN", 729);
    add("SR", "SUR", 740);
    add("SJ", "SJM", 744);
    add("SE", "SWE", 752);
    add("CH", "CHE", 756);
    add("SY", "SYR", 760);
    add("TW", "TWN", 158);
    add("TJ", "TJK", 762);
    add("TZ", "TZA", 834);
    add("TH", "THA", 764);
    add("TL", "TLS", 626);
    add("TG", "TGO", 768);
    add("TK", "TKL", 772);
    add("TO", "TON", 776);
    add("TT", "TTO", 780);
    add("TN", "TUN", 788);
    add("TM", "TKM", 795);
    add("TC", "TCA", 796);
    add("TV", "TUV", 798);
    add("TR", "TUR", 792);
    add("UG", "UGA", 800);
    add("UA", "UKR", 804);
    add("AE", "ARE", 784);
    add("GB", "GBR", 826);
    add("UM", "UMI", 581);
    add("US", "USA", 840);
    add("UY", "URY", 858);
    add("UZ", "UZB", 860);
    add("VU", "VUT", 548);
    add("VE", "VEN", 862);
    add("VN", "VNM", 704);
    add("VG", "VGB", 92);
    add("VI", "VIR", 850);
    add("WF", "WLF", 876);
    add("EH", "ESH", 732);
    add("YE", "YEM", 887);
    add("ZM", "ZMB", 894);
    add("ZW", "ZWE", 716);
    add("AX", "ALA", 248);
  }

  /**
   * Converts a country code to its corresponding ISO 3166-1 alpha-2 code.
   *
   * @param countryCode The country code to be converted. This can be in ISO 3166-1 alpha-2 format
   *     (e.g., "AR"), ISO 3166-1 alpha-3 format (e.g., "ARG"), or numeric-3 format (e.g., "032").
   * @return An {@code Optional} containing the ISO 3166-1 alpha-2 code if the conversion is
   *     successful.
   */
  public static Optional<String> convertToISO3166Code(String countryCode) {
    return Optional.ofNullable(conversions.get(countryCode.toUpperCase()));
  }
}
