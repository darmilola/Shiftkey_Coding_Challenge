package com.shiftkey.codingchallenge.domain.model

import com.google.gson.annotations.SerializedName
import com.shiftkey.codingchallenge.utils.Constants

data class ShiftModel(
    @field:SerializedName("shift_id") val id: Int,
    @field:SerializedName("start_time") val startTime: String?,
    @field:SerializedName("end_time") val endTime: String?,
    @field:SerializedName("normalized_start_date_time") val normalizedStartDateTime: String?,
    @field:SerializedName("normalized_end_date_time") val normalizedEndDateTime: String?,
    @field:SerializedName("timezone") val timezone: String?,
    @field:SerializedName("premium_rate") val isPremiumRate: Boolean?,
    @field:SerializedName("covid") val isCovid: Boolean?,
    @field:SerializedName("shift_kind") val shiftKind: String?,
    @field:SerializedName("within_distance") val withinDistance: Int?,
    @field:SerializedName("facility_type") val facilityType: FacilityTypeModel?,
    @field:SerializedName("skill") val skill: SkillModel?,
    @field:SerializedName("localized_specialty") val localizedSpecialty: LocalizedSpecialtyModel?){

    var viewType: Int = Constants.SHIFT_ITEM_TYPE_HEADER

    constructor(mViewType: Int) : this(1,"","","","","",false,false,"",0,null,null,null){
        this.viewType = mViewType
    }



}