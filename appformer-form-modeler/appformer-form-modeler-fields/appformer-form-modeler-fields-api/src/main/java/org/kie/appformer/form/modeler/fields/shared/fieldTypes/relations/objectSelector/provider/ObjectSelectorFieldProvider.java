/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.appformer.form.modeler.fields.shared.fieldTypes.relations.objectSelector.provider;

import javax.enterprise.context.Dependent;

import org.kie.appformer.form.modeler.fields.shared.fieldTypes.relations.objectSelector.definition.ObjectSelectorFieldDefinition;
import org.kie.appformer.form.modeler.fields.shared.fieldTypes.relations.objectSelector.type.ObjectSelectorFieldType;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.relations.EntityRelationField;
import org.kie.workbench.common.forms.fields.shared.fieldTypes.relations.ModelTypeFieldProvider;
import org.kie.workbench.common.forms.model.FieldDefinition;
import org.kie.workbench.common.forms.model.TypeInfo;
import org.kie.workbench.common.forms.model.TypeKind;

@Dependent
public class ObjectSelectorFieldProvider implements ModelTypeFieldProvider<ObjectSelectorFieldDefinition> {

    @Override
    public Class<ObjectSelectorFieldType> getFieldType() {
        return ObjectSelectorFieldType.class;
    }

    @Override
    public String getFieldTypeName() {
        return ObjectSelectorFieldDefinition.FIELD_TYPE.getTypeName();
    }

    @Override
    public ObjectSelectorFieldDefinition getDefaultField() {
        return new ObjectSelectorFieldDefinition();
    }

    @Override
    public ObjectSelectorFieldDefinition getFieldByType(TypeInfo typeInfo) {
        if (typeInfo.getType().equals(TypeKind.OBJECT)) {
            return getDefaultField();
        }
        return null;
    }

    @Override
    public boolean isCompatible(FieldDefinition field) {
        return field instanceof EntityRelationField;
    }
}
