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
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for converting between different formats of country codes.
 *
 * <p>
 * The {@code LocaleCountryConverter} class provides methods to convert country codes from ISO
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

  private static final int TOTAL_COUNTRIES = 249;
  private static final List<String> alpha2codes =
      List.of("AF", "AL", "DZ", "AS", "AD", "AO", "AI", "AQ", "AG", "AR", "AM", "AW", "AU", "AT",
          "AZ", "BS", "BH", "BD", "BB", "BY", "BE", "BZ", "BJ", "BM", "BT", "BO", "BQ", "BA", "BW",
          "BV", "BR", "IO", "BN", "BG", "BF", "BI", "CV", "KH", "CM", "CA", "KY", "CF", "TD", "CL",
          "CN", "CX", "CC", "CO", "KM", "CD", "CG", "CK", "CR", "HR", "CU", "CW", "CY", "CZ", "CI",
          "DK", "DJ", "DM", "DO", "EC", "EG", "SV", "GQ", "ER", "EE", "SZ", "ET", "FK", "FO", "FJ",
          "FI", "FR", "GF", "PF", "TF", "GA", "GM", "GE", "DE", "GH", "GI", "GR", "GL", "GD", "GP",
          "GU", "GT", "GG", "GN", "GW", "GY", "HT", "HM", "VA", "HN", "HK", "HU", "IS", "IN", "ID",
          "IR", "IQ", "IE", "IM", "IL", "IT", "JM", "JP", "JE", "JO", "KZ", "KE", "KI", "KP", "KR",
          "KW", "KG", "LA", "LV", "LB", "LS", "LR", "LY", "LI", "LT", "LU", "MO", "MG", "MW", "MY",
          "MV", "ML", "MT", "MH", "MQ", "MR", "MU", "YT", "MX", "FM", "MD", "MC", "MN", "ME", "MS",
          "MA", "MZ", "MM", "NA", "NR", "NP", "NL", "NC", "NZ", "NI", "NE", "NG", "NU", "NF", "MK",
          "MP", "NO", "OM", "PK", "PW", "PS", "PA", "PG", "PY", "PE", "PH", "PN", "PL", "PT", "PR",
          "QA", "RO", "RU", "RW", "RE", "BL", "SH", "KN", "LC", "MF", "PM", "VC", "WS", "SM", "ST",
          "SA", "SN", "RS", "SC", "SL", "SG", "SX", "SK", "SI", "SB", "SO", "ZA", "GS", "SS", "ES",
          "LK", "SD", "SR", "SJ", "SE", "CH", "SY", "TW", "TJ", "TZ", "TH", "TL", "TG", "TK", "TO",
          "TT", "TN", "TM", "TC", "TV", "TR", "UG", "UA", "AE", "GB", "UM", "US", "UY", "UZ", "VU",
          "VE", "VN", "VG", "VI", "WF", "EH", "YE", "ZM", "ZW", "AX");
  private static final List<String> alpha3codes = List.of("AFG", "ALB", "DZA", "ASM", "AND", "AGO",
      "AIA", "ATA", "ATG", "ARG", "ARM", "ABW", "AUS", "AUT", "AZE", "BHS", "BHR", "BGD", "BRB",
      "BLR", "BEL", "BLZ", "BEN", "BMU", "BTN", "BOL", "BES", "BIH", "BWA", "BVT", "BRA", "IOT",
      "BRN", "BGR", "BFA", "BDI", "CPV", "KHM", "CMR", "CAN", "CYM", "CAF", "TCD", "CHL", "CHN",
      "CXR", "CCK", "COL", "COM", "COD", "COG", "COK", "CRI", "HRV", "CUB", "CUW", "CYP", "CZE",
      "CIV", "DNK", "DJI", "DMA", "DOM", "ECU", "EGY", "SLV", "GNQ", "ERI", "EST", "SWZ", "ETH",
      "FLK", "FRO", "FJI", "FIN", "FRA", "GUF", "PYF", "ATF", "GAB", "GMB", "GEO", "DEU", "GHA",
      "GIB", "GRC", "GRL", "GRD", "GLP", "GUM", "GTM", "GGY", "GIN", "GNB", "GUY", "HTI", "HMD",
      "VAT", "HND", "HKG", "HUN", "ISL", "IND", "IDN", "IRN", "IRQ", "IRL", "IMN", "ISR", "ITA",
      "JAM", "JPN", "JEY", "JOR", "KAZ", "KEN", "KIR", "PRK", "KOR", "KWT", "KGZ", "LAO", "LVA",
      "LBN", "LSO", "LBR", "LBY", "LIE", "LTU", "LUX", "MAC", "MDG", "MWI", "MYS", "MDV", "MLI",
      "MLT", "MHL", "MTQ", "MRT", "MUS", "MYT", "MEX", "FSM", "MDA", "MCO", "MNG", "MNE", "MSR",
      "MAR", "MOZ", "MMR", "NAM", "NRU", "NPL", "NLD", "NCL", "NZL", "NIC", "NER", "NGA", "NIU",
      "NFK", "MKD", "MNP", "NOR", "OMN", "PAK", "PLW", "PSE", "PAN", "PNG", "PRY", "PER", "PHL",
      "PCN", "POL", "PRT", "PRI", "QAT", "ROU", "RUS", "RWA", "REU", "BLM", "SHN", "KNA", "LCA",
      "MAF", "SPM", "VCT", "WSM", "SMR", "STP", "SAU", "SEN", "SRB", "SYC", "SLE", "SGP", "SXM",
      "SVK", "SVN", "SLB", "SOM", "ZAF", "SGS", "SSD", "ESP", "LKA", "SDN", "SUR", "SJM", "SWE",
      "CHE", "SYR", "TWN", "TJK", "TZA", "THA", "TLS", "TGO", "TKL", "TON", "TTO", "TUN", "TKM",
      "TCA", "TUV", "TUR", "UGA", "UKR", "ARE", "GBR", "UMI", "USA", "URY", "UZB", "VUT", "VEN",
      "VNM", "VGB", "VIR", "WLF", "ESH", "YEM", "ZMB", "ZWE", "ALA");
  private static final List<Integer> numericCodes = List.of(4, 8, 12, 16, 20, 24, 660, 10, 28, 32,
      51, 533, 36, 40, 31, 44, 48, 50, 52, 112, 56, 84, 204, 60, 64, 68, 535, 70, 72, 74, 76, 86,
      96, 100, 854, 108, 132, 116, 120, 124, 136, 140, 148, 152, 156, 162, 166, 170, 174, 180, 178,
      184, 188, 191, 192, 531, 196, 203, 384, 208, 262, 212, 214, 218, 818, 222, 226, 232, 233, 748,
      231, 238, 234, 242, 246, 250, 254, 258, 260, 266, 270, 268, 276, 288, 292, 300, 304, 308, 312,
      316, 320, 831, 324, 624, 328, 332, 334, 336, 340, 344, 348, 352, 356, 360, 364, 368, 372, 833,
      376, 380, 388, 392, 832, 400, 398, 404, 296, 408, 410, 414, 417, 418, 428, 422, 426, 430, 434,
      438, 440, 442, 446, 450, 454, 458, 462, 466, 470, 584, 474, 478, 480, 175, 484, 583, 498, 492,
      496, 499, 500, 504, 508, 104, 516, 520, 524, 528, 540, 554, 558, 562, 566, 570, 574, 807, 580,
      578, 512, 586, 585, 275, 591, 598, 600, 604, 608, 612, 616, 620, 630, 634, 642, 643, 646, 638,
      652, 654, 659, 662, 663, 666, 670, 882, 674, 678, 682, 686, 688, 690, 694, 702, 534, 703, 705,
      90, 706, 710, 239, 728, 724, 144, 729, 740, 744, 752, 756, 760, 158, 762, 834, 764, 626, 768,
      772, 776, 780, 788, 795, 796, 798, 792, 800, 804, 784, 826, 581, 840, 858, 860, 548, 862, 704,
      92, 850, 876, 732, 887, 894, 716, 248);

  private static final Map<String, String> conversions = getConversionsMap();

  /**
   * Converts a country code to its corresponding ISO 3166-1 alpha-2 code.
   *
   * @param countryCode The country code to be converted. This can be in ISO 3166-1 alpha-2 format
   *        (e.g., "AR"), ISO 3166-1 alpha-3 format (e.g., "ARG"), or numeric-3 format (e.g.,
   *        "032").
   * @return An {@link Optional} containing the ISO 3166-1 alpha-2 code if the conversion is
   *         successful.
   */
  public static Optional<String> convertToISO3166Code(String countryCode) {
    return Optional.ofNullable(conversions.get(countryCode.toUpperCase()));
  }

  private static Map<String, String> getConversionsMap() {
    var conversions = new HashMap<String, String>();

    for (int i = 0; i < TOTAL_COUNTRIES; i++) {
      String alpha2 = alpha2codes.get(i);
      conversions.put(alpha2, alpha2);
      conversions.put(alpha3codes.get(i), alpha2);
      conversions.put(numericCodes.get(i).toString(), alpha2);
    }

    return conversions;
  }

}
