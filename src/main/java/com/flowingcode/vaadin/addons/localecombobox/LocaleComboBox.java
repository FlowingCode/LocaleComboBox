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
   * Creates a new instance of LocaleComboBox with all the installed locales.
   */
  public LocaleComboBox() {
    setItemLabelGenerator(Locale::getDisplayName);
    setRenderer(getLocaleRenderer());
    addValueChangeListener(this::onValueChange);
    setItems(
        Arrays.stream(Locale.getAvailableLocales()).filter(loc -> loc.getCountry().length() == 2)
            .sorted((l1, l2) -> l1.getDisplayName().compareTo(l2.getDisplayName())).toList());
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
        .withProperty("countryName", loc -> loc.getDisplayCountry())
        .withProperty("displayName", loc -> loc.getDisplayName());
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
