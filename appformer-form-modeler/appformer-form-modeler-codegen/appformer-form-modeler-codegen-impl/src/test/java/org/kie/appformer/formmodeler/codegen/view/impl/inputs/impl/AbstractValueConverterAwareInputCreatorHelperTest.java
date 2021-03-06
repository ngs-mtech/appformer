/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.appformer.formmodeler.codegen.view.impl.inputs.impl;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.kie.appformer.formmodeler.codegen.view.impl.java.inputs.InputCreatorHelper;
import org.kie.appformer.formmodeler.codegen.view.impl.java.inputs.RequiresValueConverter;
import org.kie.workbench.common.forms.model.FieldDefinition;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public abstract class AbstractValueConverterAwareInputCreatorHelperTest<HELPER extends InputCreatorHelper<FIELD> & RequiresValueConverter<FIELD>, FIELD extends FieldDefinition> {

    List<String> oldConverterClassNames = new ArrayList<>();

    @Mock
    FIELD mockFieldDefinition;

    HELPER helper;

    @Before
    public void init() {
        oldConverterClassNames.addAll(getOldConverterClassNames());
        helper = getHelper();
    }

    @Test
    public void testGetSupportedFieldTypeCode() {
        String expectedTypeCode = getFieldTypeName();
        String actualTypeCode = helper.getSupportedFieldTypeCode();
        assertEquals(actualTypeCode,
                     expectedTypeCode);
    }

    @Test
    public void testGetInputWidget() {
        String expectedInputWidget = getInputWidget();
        String actualInputWidget = helper.getInputWidget(null);
        assertEquals(actualInputWidget,
                     expectedInputWidget);
    }

    protected void testGetConverterClassName(Class fieldClass,
                                             String expectedConverter) {

        Mockito.when(mockFieldDefinition.getStandaloneClassName()).thenReturn(fieldClass.getName());

        String actualConverter = helper.getConverterClassName(mockFieldDefinition);

        // Checking possible regression if actualConverter is using any of the old converters provided by the widget...
        assertFalse("Regression here! The converter className generated by the helper is using an old className",
                    oldConverterClassNames.contains(actualConverter));

        assertEquals(expectedConverter,
                     actualConverter);
    }

    abstract HELPER getHelper();

    abstract List<String> getOldConverterClassNames();

    abstract String getFieldTypeName();

    abstract String getInputWidget();
}
