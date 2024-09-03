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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.FlexComponent.Alignment;
import com.vaadin.flow.component.orderedlayout.FlexComponent.JustifyContentMode;

@SuppressWarnings("serial")
public class BaseLocaleComboBoxDemo extends Div {

  protected HorizontalLayout createHorizontalContainer(String title, LocaleComboBox combo) {
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

  protected HorizontalLayout createHorizontalContainer(Component component, LocaleComboBox combo) {
    HorizontalLayout container = new HorizontalLayout();
    container.setWidthFull();
    container.setAlignItems(Alignment.CENTER);
    container.setJustifyContentMode(JustifyContentMode.BETWEEN);
    container.add(component, combo);
    container.expand(combo);
    return container;
  }

}
