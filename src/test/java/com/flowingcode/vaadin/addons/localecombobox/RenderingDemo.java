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
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

@DemoSource
@PageTitle("Flag rendering")
@SuppressWarnings("serial")
@Route(value = "locale-combo-box/rendering", layout = LocaleComboBoxDemoView.class)
public class RenderingDemo extends BaseLocaleComboBoxDemo {
  public RenderingDemo() {

    List<Locale> localeList =
        Arrays.stream(Locale.getAvailableLocales())
            .filter(loc -> !loc.getDisplayName().isBlank())
            .sorted((l1, l2) -> l1.getDisplayName().compareTo(l2.getDisplayName()))
            .toList();

    LocaleComboBox defaultLocaleCombo = new LocaleComboBox(localeList);
    LocaleComboBox flagsLocaleCombo = new LocaleComboBox(localeList);
    Checkbox checkbox = new Checkbox("Render flags");

    defaultLocaleCombo.setValue(Locale.ITALY);
    flagsLocaleCombo.setValue(Locale.ITALY);
    checkbox.setValue(true);

    /*
     * You can choose whether the flags should be displayed alongside the locale names by using the
     * setHasFlag method
     */
    checkbox.addValueChangeListener(event -> flagsLocaleCombo.setHasFlags(event.getValue()));

    // #if vaadin eq 0
    add(createHorizontalContainer("Flags are rendered by default:", defaultLocaleCombo));
    add(createHorizontalContainer(checkbox, flagsLocaleCombo));
    // #endif
    // show-source add(defaultLocaleCombo);
    // show-source add(checkbox, flagsLocaleCombo);

  }
}
