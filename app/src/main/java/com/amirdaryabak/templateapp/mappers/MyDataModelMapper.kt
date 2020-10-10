package com.amirdaryabak.templateapp.mappers

import com.amirdaryabak.templateapp.api.responses.MyDataModelResponse
import com.amirdaryabak.templateapp.db.tables.MyDataModel
import com.amirdaryabak.templateapp.utils.EntityMapper
import javax.inject.Inject

class MyDataModelMapper
@Inject
constructor(): EntityMapper<MyDataModelResponse, MyDataModel> {

    override fun mapFromEntity(entity: MyDataModelResponse): MyDataModel {
        return MyDataModel(
            id = entity.id,
            name = "",
        )
    }

    override fun mapToEntity(domainModel: MyDataModel): MyDataModelResponse {
        return MyDataModelResponse(
            id = domainModel.id!!,
        )
    }


    fun mapFromEntityList(entities: List<MyDataModelResponse>): List<MyDataModel>{
        return entities.map { mapFromEntity(it) }
    }

}





















