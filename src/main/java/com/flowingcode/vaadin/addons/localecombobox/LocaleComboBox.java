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

import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.data.renderer.LitRenderer;
import java.util.Arrays;
import java.util.Locale;

/**
 * Vaadin ComboBox extension that allows to choose between multiple locales.
 * 
 * @author Tomas Peiretti / Flowing Code
 */
@SuppressWarnings("serial")
@NpmPackage(value = "flag-icons", version = "7.2.3")
@CssImport("flag-icons/css/flag-icons.min.css")
@CssImport("styles/locale-combo-box.css")
public class LocaleComboBox extends ComboBox<Locale> {

  private static final String ITEM_LAYOUT_CLASS_NAME = "fc-locale-combo-box-item-layout";
  private static final String ITEM_FLAG_CLASS_NAME = "fc-locale-combo-box-item-flag";

  /**
   * Constant for the default display mode.
   * <p>
   * In this mode, the Locale names are displayed using the default locale's display format.
   */
  public static final int DISPLAY_DEFAULT = 0;
  /**
   * Constant for the locale-specific display mode.
   * <p>
   * In this mode, the Locale names are displayed using the formatting of the specific locale set by
   * {@link #setDisplayLocale(Locale)}.
   */
  public static final int DISPLAY_CUSTOM = 1;
  /**
   * Constant for the selected display mode.
   * <p>
   * In this mode, the Locale names are displayed using the formatting of the currently selected
   * locale.
   */
  public static final int DISPLAY_SELECTED = 2;

  private int displayMode = DISPLAY_DEFAULT;
  private Locale customDisplayLocale = Locale.getDefault();

  /**
   * Creates a new instance of LocaleComboBox with all the installed locales.
   */
  public LocaleComboBox() {
    setItemLabelGenerator(item -> item.getDisplayName(getLocaleForDisplay()));
    setRenderer(getLocaleRenderer());
    addValueChangeListener(this::onValueChange);
    setItems(
        Arrays.stream(Locale.getAvailableLocales()).filter(loc -> loc.getCountry().length() == 2)
            .sorted((l1, l2) -> l1.getDisplayName().compareTo(l2.getDisplayName())).toList());
  }

  /**
   * Sets the display mode of the LocaleComboBox.
   * <p>
   * The display mode determines how the Locale names are presented in the combo box:
   * <ul>
   * <li>{@link #DISPLAY_DEFAULT} - Uses the default locale's display format to show Locale
   * names.</li>
   * <li>{@link #DISPLAY_CUSTOM} - Uses the specific locale (set by
   * {@link #setDisplayLocale(Locale)}) to format the Locale names.</li>
   * <li>{@link #DISPLAY_SELECTED} - Uses the currently selected locale to format the Locale
   * names.</li>
   * </ul>
   * 
   * @param displayMode the display mode to use; must be one of {@link #DISPLAY_DEFAULT},
   *        {@link #DISPLAY_CUSTOM}, or {@link #DISPLAY_SELECTED}.
   */
  public void setDisplayMode(int displayMode) {
    this.displayMode = displayMode;
  }

  /**
   * Sets the locale used for formatting Locale names when {@link #DISPLAY_LOCALE} mode is active.
   * <p>
   * This locale determines how Locale names are formatted when {@link #DISPLAY_LOCALE} is selected
   * as the display mode. If the display mode is {@link #DISPLAY_DEFAULT}, this setting is ignored.
   * 
   * @param displayLocale the locale to use for formatting.
   */
  public void setDisplayLocale(Locale displayLocale) {
    this.customDisplayLocale = displayLocale == null ? Locale.getDefault() : displayLocale;
  }

  private LitRenderer<Locale> getLocaleRenderer() {
    return LitRenderer
        .<Locale>of(
            """
                <vaadin-horizontal-layout class="${item.layoutClass}">
                    <span class="fi fi-${item.countryCode} ${item.flagClass} alt="${item.countryName}'s flag"></span>
                    <span>${item.displayName}</span>
                </vaadin-horizontal-layout>""")
        .withProperty("layoutClass", loc -> ITEM_LAYOUT_CLASS_NAME)
        .withProperty("flagClass", loc -> ITEM_FLAG_CLASS_NAME)
        .withProperty("countryCode", loc -> loc.getCountry().toLowerCase())
        .withProperty("countryName", loc -> loc.getDisplayCountry(getLocaleForDisplay()))
        .withProperty("displayName", loc -> loc.getDisplayName(getLocaleForDisplay()));
  }

  private Locale getLocaleForDisplay() {

    switch (displayMode) {

      case DISPLAY_CUSTOM:
        return customDisplayLocale;

      case DISPLAY_SELECTED:
        return this.getValue() == null ? Locale.getDefault() : this.getValue();

      default:
        return Locale.getDefault();
    }
  }

  private void onValueChange(ComponentValueChangeEvent<ComboBox<Locale>, Locale> event) {
    Locale newValue = event.getValue();
    if (newValue == null) {
      setPrefixComponent(null);
      return;
    }
    Span flagIcon = new Span();
    flagIcon.addClassNames("fi", "fi-" + newValue.getCountry().toLowerCase());
    setPrefixComponent(flagIcon);
  }

}
