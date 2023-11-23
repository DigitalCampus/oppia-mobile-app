package org.digitalcampus.oppiamobile.data.utils.mappers

import org.digitalcampus.oppiamobile.data.user.db.entity.CustomFieldEntity
import org.digitalcampus.oppiamobile.domain.model.FormField
import org.digitalcampus.oppiamobile.domain.model.FormFieldType

class CustomFieldEntityMapper : EntityMapper<CustomFieldEntity, FormField> {
    override fun mapFromEntity(entity: CustomFieldEntity): FormField {
        return FormField(
            name = entity.key,
            label = entity.label,
            required = entity.required,
            type = FormFieldType.valueOf(entity.type),
            helperText = entity.helpText,
        )
    }

    override fun mapToEntity(domainModel: FormField): CustomFieldEntity {
        return CustomFieldEntity(
            key = domainModel.name,
            label = domainModel.label,
            required = domainModel.required,
            type = domainModel.type.name,
            helpText = domainModel.helperText,
            order = 0,
            collectionBy = null,
            collectionName = null,
            visibleBy = null,
            visibleValue = null,
        )
    }
}
