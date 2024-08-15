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

import com.flowingcode.vaadin.addons.demo.DemoSource;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Locale;

@DemoSource
@PageTitle("Display modes")
@SuppressWarnings("serial")
@Route(value = "demo", layout = LocaleComboBoxDemoView.class)
public class LocaleComboBoxDemo extends Div {

  public LocaleComboBoxDemo() {

    LocaleComboBox defaultDisplayLocale = new LocaleComboBox();
    LocaleComboBox koreanLocaleCombo = new LocaleComboBox();
    LocaleComboBox selectedLocaleCombo = new LocaleComboBox();

    defaultDisplayLocale.setValue(Locale.ITALY);
    
    koreanLocaleCombo.setDisplayLocale(Locale.KOREA);
    koreanLocaleCombo.setDisplayMode(LocaleComboBox.DisplayMode.CUSTOM);
    koreanLocaleCombo.setValue(Locale.ITALY);
    
    selectedLocaleCombo.setDisplayMode(LocaleComboBox.DisplayMode.SELECTED);
    selectedLocaleCombo.setValue(Locale.ITALY);
    
    // #if vaadin eq 0
    add(createHorizontalContainer("Default display mode (uses default locale):", defaultDisplayLocale),
        createHorizontalContainer("Display locales with Korean locale:", koreanLocaleCombo),
        createHorizontalContainer("Display locales with selected locale:", selectedLocaleCombo));
    // #endif
    // show-source add(defaultDisplayLocale);
    // show-source add(koreanLocaleCombo);
    // show-source add(selectedLocaleCombo);
  }

  // #if vaadin eq 0
  private HorizontalLayout createHorizontalContainer(String title, LocaleComboBox combo) {
    Span titleSpan = new Span(new Text(title));
    titleSpan.setWidth("300px");
    HorizontalLayout container = new HorizontalLayout();
    container.setWidthFull();
    container.setAlignItems(Alignment.CENTER);
    container.setJustifyContentMode(JustifyContentMode.BETWEEN);
    container.add(titleSpan, combo);
    container.expand(combo);
    return container;
  }
  // #endif

}
