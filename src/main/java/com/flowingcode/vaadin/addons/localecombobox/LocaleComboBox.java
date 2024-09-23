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
import java.util.Collection;
import java.util.Locale;
import java.util.Optional;

/**
 * Vaadin ComboBox extension that allows to choose between multiple locales.
 *
 * @author Tomas Peiretti / Flowing Code
 */
@SuppressWarnings("serial")
@NpmPackage(value = "flag-icons", version = "7.2.3")
@CssImport("flag-icons/css/flag-icons.min.css")
@CssImport("./styles/locale-combo-box.css")
public class LocaleComboBox extends ComboBox<Locale> {

  private static final String ITEM_LAYOUT_CLASS_NAME = "fc-locale-combo-box-item-layout";
  private static final String ITEM_FLAG_CLASS_NAME = "fc-locale-combo-box-item-flag";
  private static final String DEFAULT_FLAG_CODE = "un";

  /**
   * Represents the built-in display modes for locale names in the {@link LocaleComboBox} component.
   *
   * <p>These enums can be used in {@link #setDisplayMode(DisplayMode)} to easily switch between the
   * built-in display modes.
   */
  public enum DisplayMode {

    /**
     * Default display mode.
     *
     * <p>In this mode, the Locale names are displayed using the default locale's display format.
     */
    DEFAULT,

    /**
     * Selected display mode.
     *
     * <p>In this mode, the Locale names are displayed using the formatting of the currently
     * selected locale.
     */
    SELECTED,

    /**
     * Custom display mode.
     *
     * <p>In this mode, the Locale names are displayed using the formatting of the specific locale
     * set by {@link #setDisplayLocale(Locale)}.
     */
    CUSTOM;
  }

  private DisplayMode displayMode = DisplayMode.DEFAULT;
  private Locale customDisplayLocale;

  /** Indicates whether the flags should be displayed alongside the locale names. */
  private boolean hasFlags = true;

  /** Creates a new instance of {@code LocaleComboBox}. */
  public LocaleComboBox() {
    setItemLabelGenerator(item -> item.getDisplayName(getLocaleForDisplay()));
    setRenderer(getLocaleRenderer());
    addValueChangeListener(this::onValueChange);
  }

  /**
   * Creates a new instance of {@code LocaleComboBox} with the desired locales
   *
   * @param locales the {@code Collection} of {@code Locale} to include in the combobox
   */
  public LocaleComboBox(Collection<Locale> locales) {
    this();
    setItems(locales);
  }

  /**
   * Sets the display mode of the LocaleComboBox.
   *
   * @param displayMode the display mode to use
   * @see DisplayMode
   */
  public void setDisplayMode(DisplayMode displayMode) {
    this.displayMode = displayMode;
  }

  /**
   * Sets the locale used for formatting Locale names when {@link DisplayMode#CUSTOM} mode is
   * active.
   *
   * <p>This locale determines how Locale names are formatted when {@link DisplayMode#CUSTOM} is
   * selected as the display mode. If the display mode is any other than {@link DisplayMode#CUSTOM},
   * this setting is ignored.
   *
   * @param displayLocale the {@code Locale} to use for formatting.
   */
  public void setDisplayLocale(Locale displayLocale) {
    this.customDisplayLocale = displayLocale;
  }

  /**
   * Returns the current flag display status.
   *
   * @return {@code true} if flags are displayed alongside the locale names, {@code false} otherwise
   */
  public boolean hasFlags() {
    return hasFlags;
  }

  /**
   * Sets whether flags should be displayed alongside locale names.
   *
   * <p>This method updates the internal state to reflect whether flags should be displayed and
   * updates the rendering based on the new state.
   *
   * @param hasFlags A {@code boolean} indicating whether flags should be displayed or not.
   */
  public void setHasFlags(boolean hasFlags) {
    this.hasFlags = hasFlags;
    this.setRenderer(this.hasFlags ? getLocaleRenderer() : getLocaleRendererWithoutFlags());
    this.setPrefixFlag(this.hasFlags ? this.getValue() : null);
  }

  private LitRenderer<Locale> getLocaleRenderer() {
    return LitRenderer.<Locale>of(
            """
                <vaadin-horizontal-layout class="${item.layoutClass}">
                    <span class="fi fi-${item.countryCode} ${item.flagClass} alt="${item.countryName}'s flag"></span>
                    <span>${item.displayName}</span>
                </vaadin-horizontal-layout>""")
        .withProperty("layoutClass", loc -> ITEM_LAYOUT_CLASS_NAME)
        .withProperty("flagClass", loc -> ITEM_FLAG_CLASS_NAME)
        .withProperty("countryCode", this::getFlagCode)
        .withProperty("countryName", loc -> loc.getDisplayCountry(getLocaleForDisplay()))
        .withProperty("displayName", loc -> loc.getDisplayName(getLocaleForDisplay()));
  }

  private LitRenderer<Locale> getLocaleRendererWithoutFlags() {
    return LitRenderer.<Locale>of(
            """
        <vaadin-horizontal-layout class="${item.layoutClass}">
            <span>${item.displayName}</span>
        </vaadin-horizontal-layout>""")
        .withProperty("layoutClass", loc -> ITEM_LAYOUT_CLASS_NAME)
        .withProperty("displayName", loc -> loc.getDisplayName(getLocaleForDisplay()));
  }

  private Locale getLocaleForDisplay() {

    switch (displayMode) {
      case CUSTOM:
        return Optional.ofNullable(customDisplayLocale).orElseGet(this::getLocale);

      case SELECTED:
        return Optional.ofNullable(this.getValue()).orElseGet(this::getLocale);

      default:
        return this.getLocale();
    }
  }

  private String getFlagCode(Locale locale) {
    String countryCode = locale.getCountry();
    return LocaleCountryConverter.convertToISO3166Code(countryCode)
        .map(String::toLowerCase)
        .orElse(DEFAULT_FLAG_CODE);
  }

  private void onValueChange(ComponentValueChangeEvent<ComboBox<Locale>, Locale> event) {

    if (!this.hasFlags) {
      return;
    }

    Locale newValue = event.getValue();
    this.setPrefixFlag(newValue);
  }

  private void setPrefixFlag(Locale locale) {
    if (locale == null) {
      setPrefixComponent(null);
      return;
    }

    Span flagIcon = new Span();
    flagIcon.addClassNames("fi", "fi-" + this.getFlagCode(locale));
    setPrefixComponent(flagIcon);
  }
}
