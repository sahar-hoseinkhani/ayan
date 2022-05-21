package ir.ayantech.pishkhancore.model

import ir.ayantech.pishkhancore.R

object Product {
    const val CloudStorageUploadFiles = "CloudStorageUploadFiles"
    const val CloudStorageDownloadFiles = "CloudStorageDownloadFiles"
    const val InquiryWaterBill = "InquiryWaterBill"
    const val InquiryElectricBill = "InquiryElectricBill"
    const val InquiryGasBill = "InquiryGasBill"
    const val InquiryLandlinePhoneBill = "InquiryLandlinePhoneBill"
    const val InquiryMciCellPhoneBill = "InquiryMciCellPhoneBill"
    const val InquiryIrancellCellPhoneBill = "InquiryIrancellCellPhoneBill"
    const val InquiryRightelCellPhoneBill = "InquiryRightelCellPhoneBill"
    const val InquiryCarTrafficFinesBill = "InquiryCarTrafficFinesBill"
    const val InquiryMotorcycleTrafficFinesBill = "InquiryMotorcycleTrafficFinesBill"
    const val InquiryTrafficPlanTollBill = "InquiryTrafficPlanTollBill"
    const val InquiryFreewayTollBill = "InquiryFreewayTollBill"
    const val InquiryDrivingNegativePoint = "InquiryDrivingNegativePoint"
    const val InquiryDrivingAccidents = "InquiryDrivingAccidents"
    const val InquiryThirdPartyInsurance = "InquiryThirdPartyInsurance"
    const val InquiryRevokedPlateNumber = "InquiryRevokedPlateNumber"
    const val InquiryTechnicalExaminationCertificate = "InquiryTechnicalExaminationCertificate"
    const val InquiryCarFuelCardPostPackageStatus = "InquiryCarFuelCardPostPackageStatus"
    const val InquiryCarIdentificationDocumentsPostPackageStatus =
        "InquiryCarIdentificationDocumentsPostPackageStatus"
    const val InquiryCarIdentificationCardPostPackageStatus =
        "InquiryCarIdentificationCardPostPackageStatus"
    const val InquiryMotorcycleIdentificationDocumentsPostPackageStatus =
        "InquiryMotorcycleIdentificationDocumentsPostPackageStatus"
    const val InquiryDrivingLicencePostPackageStatus = "InquiryDrivingLicencePostPackageStatus"
    const val InquiryMilitaryServiceCardPostPackageStatus =
        "InquiryMilitaryServiceCardPostPackageStatus"
    const val InquiryAdministrativeJusticePostPackageStatus =
        "InquiryAdministrativeJusticePostPackageStatus"
    const val InquiryJusticeShares = "InquiryJusticeShares"
    const val InquirySocialSecurityInsuranceHistory = "InquirySocialSecurityInsuranceHistory"
    const val InquiryAirPolutionControl = "InquiryAirPolutionControl"
    const val InquiryTopUpCharge = "InquiryTopUpCharge"
    const val InquiryTehranMunicipalityBuildingTollBill =
        "InquiryTehranMunicipalityBuildingTollBill"
    const val InquiryMunicipalityPropertyTollBill = "InquiryMunicipalityPropertyTollBill"
    const val InquiryTopUpInternetPackage = "InquiryTopUpInternetPackage"
    const val InquirySubventionPaymentHistory = "InquirySubventionPaymentHistory"
    const val InquiryJusticeSharesValue = "InquiryJusticeSharesValue"
    const val InquirySocialSecurityPensionReceipt = "InquirySocialSecurityPensionReceipt"
    const val InquirySocialSecurityPension = "InquirySocialSecurityPension"
    const val InquiryGovernmentRetirement = "InquiryGovernmentRetirement"
    const val ChooseServices = "ChooseServices"
    const val Exchange = "Exchange"
    const val Coin = "Coin"
    const val Gold = "Gold"
    const val CryptoCurrency = "CryptoCurrency"
    const val Sheba = "Sheba"
    const val USSDOperator = "USSDOperator"
    const val USSDCommon = "USSDCommon"
    const val InquiryPostPackageTracking = "InquiryPostPackageTracking"
    const val InquiryIbanByAccountNumber = "InquiryIbanByAccountNumber"
    const val InquiryIbanByCardNumber = "InquiryIbanByCardNumber"
    const val InquiryAccountNumberByIban = "InquiryAccountNumberByIban"
    const val InquirySocialSecurityInsuranceMedicalCredit =
        "InquirySocialSecurityInsuranceMedicalCredit"
    const val InquiryMunicipalityTaxiFaresBill = "InquiryMunicipalityTaxiFaresBill"
    const val InquiryMunicipalityCarAnnualTollBill = "InquiryMunicipalityCarAnnualTollBill"
    const val InquiryMunicipalityCarTollBill = "InquiryMunicipalityCarTollBill"

    const val InquiryExchangeMarketDataForeignCurrency = "InquiryExchangeMarketDataForeignCurrency"
    const val InquiryExchangeMarketDataCryptoCurrency = "InquiryExchangeMarketDataCryptoCurrency"
    const val InquiryExchangeMarketDataGold = "InquiryExchangeMarketDataGold"
    const val SocialSecurityUnemploymentInsurance = "SocialSecurityUnemploymentInsurance"
    const val InquiryEBankSayadCheque = "InquirySayadCheque"
    const val InquiryCarFuelCardPostPackageNumber = "InquiryCarFuelCardPostPackageNumber"
    const val InquirySocialSecurityHealthInsurance = "InquirySocialSecurityHealthInsurance"
    const val InquiryGovernmentRetirementReceipt = "InquiryGovernmentRetirementReceipt"

    const val SubscriptionPlan = "SubscriptionPlan"
    const val NativeAd = "NativeAd"

    const val PENSION_RECEIPT = "pension_receipt"
    const val RETIREMENT = "retirement"
    const val INSURANCE = "insurance"
    const val MOBILE = "mobile"
}

//fun String.getProductShowName() = when (this) {
//    Product.InquiryWaterBill -> "آب"
//    Product.InquiryElectricBill -> "برق"
//    Product.InquiryGasBill -> "گاز"
//    Product.InquiryLandlinePhoneBill -> "تلفن ثابت"
//    Product.InquiryMciCellPhoneBill -> "همراه اول"
//    Product.InquiryIrancellCellPhoneBill -> "ایرانسل"
//    Product.InquiryRightelCellPhoneBill -> "رایتل"
//    Product.InquiryCarTrafficFinesBill -> "خلافی خودرو"
//    Product.InquiryMotorcycleTrafficFinesBill -> "خلافی موتور"
//    Product.InquiryTrafficPlanTollBill -> "عوارض طرح ترافیک"
//    Product.InquiryFreewayTollBill -> "عوارض آزاد راه"
//    Product.InquiryDrivingNegativePoint -> "نمره منفی"
//    Product.InquiryDrivingAccidents -> "تصادفات رانندگی"
//    Product.InquiryThirdPartyInsurance -> "بیمه شخص ثالث"
//    Product.InquiryRevokedPlateNumber -> "پلاک فک‌شده"
//    Product.InquiryTechnicalExaminationCertificate -> "معاینه فنی"
//    Product.InquiryCarFuelCardPostPackageStatus -> "کارت سوخت"
//    Product.InquiryCarFuelCardPostPackageNumber -> "کارت سوخت"
//    Product.InquiryCarIdentificationDocumentsPostPackageStatus -> "شناسنامه خودرو"
//    Product.InquiryCarIdentificationCardPostPackageStatus -> "کارت خودرو"
//    Product.InquiryMotorcycleIdentificationDocumentsPostPackageStatus -> "اسناد موتور سیکلت"
//    Product.InquiryDrivingLicencePostPackageStatus -> "گواهی نامه"
//    Product.InquiryMilitaryServiceCardPostPackageStatus -> "نظام وظیفه"
//    Product.InquiryAdministrativeJusticePostPackageStatus -> "دیوان عدالت"
//    Product.InquiryJusticeShares -> "سهام عدالت"
//    Product.InquirySocialSecurityInsuranceHistory -> "سوابق بیمه"
//    Product.InquiryAirPolutionControl -> "طرح کنترل آلودگی"
//    Product.InquiryTehranMunicipalityBuildingTollBill -> "عوارض ملک"
//    Product.InquiryMunicipalityPropertyTollBill -> "عوارض ملک"
//    Product.InquiryTopUpCharge -> "خرید شارژ"
//    Product.InquiryTopUpInternetPackage -> "بسته اینترنتی"
//    Product.InquirySubventionPaymentHistory -> "یارانه نقدی"
//    Product.InquirySocialSecurityPensionReceipt -> "فیش حقوقی بازنشستگان تامین اجتماعی"
//    Product.InquirySocialSecurityPension -> "حکم بازنشستگان تامین اجتماعی"
//    Product.InquiryJusticeSharesValue -> "سهام عدالت"
//    Product.InquiryGovernmentRetirement -> "حکم بازنشستگان کشوری"
//    Product.InquiryMunicipalityTaxiFaresBill -> "کرایه تاکسی"
//    Product.Exchange -> "ارز"
//    Product.Coin -> "سکه و طلا"
//    Product.Gold -> "طلا"
//    Product.InquiryExchangeMarketDataForeignCurrency -> "ارز"
//    Product.InquiryExchangeMarketDataCryptoCurrency -> "ارز دیجیتال"
//    Product.InquiryExchangeMarketDataGold -> "سکه و طلا"
//    Product.CryptoCurrency -> "ارز دیجیتال"
//    Product.ChooseServices -> "انتخاب خدمات"
//    Product.USSDCommon -> "کدهای دستوری"
//    Product.USSDOperator -> "کدهای اپراتورها"
//    Product.InquiryPostPackageTracking -> "پیگیری مرسولات"
//    Product.Sheba -> "تبدیل شبا"
//    Product.InquiryIbanByAccountNumber -> "شماره حساب به شبا"
//    Product.InquiryIbanByCardNumber -> "شماره کارت به شبا"
//    Product.InquiryAccountNumberByIban -> "شبا به شماره حساب"
//    Product.InquirySocialSecurityInsuranceMedicalCredit -> "دفترچه بیمه تامین اجتماعی"
//    Product.InquiryMunicipalityCarAnnualTollBill -> "عوارض سالیانه خودرو"
//    Product.InquiryMunicipalityCarTollBill -> "عوارض شهرداری خودرو"
//    Product.InquiryEBankSayadCheque -> "چک صیادی"
//    Product.InquirySocialSecurityHealthInsurance -> "دفترچه بیمه سلامت"
//    Product.SubscriptionPlan -> "اشتراک طلایی"
//    Product.SocialSecurityUnemploymentInsurance -> "محاسبه بیمه بیکاری"
//    Product.InquiryGovernmentRetirementReceipt -> "فیش حقوقی بازنشستگان کشوری"
//
//    Product.MOBILE -> "تلفن همراه"
//    Product.RETIREMENT -> "حکم بازنشستگی"
//    Product.PENSION_RECEIPT -> "فیش حقوقی"
//    Product.INSURANCE -> "دفترچه بیمه"
//    else -> "محصول جدید!"
//}

fun String.getProductIcon() = when (this) {
//    Product.CloudStorageUploadFiles -> R.drawable.ic_logo
//    Product.CloudStorageDownloadFiles -> R.drawable.ic_logo
    Product.InquiryWaterBill -> R.drawable.ic_waterbill
    Product.InquiryElectricBill -> R.drawable.ic_electricbill
    Product.InquiryGasBill -> R.drawable.ic_gasbill
    Product.InquiryLandlinePhoneBill -> R.drawable.ic_landlinephonebill
    Product.InquiryMciCellPhoneBill -> R.drawable.ic_mcicellphonebill
    Product.InquiryIrancellCellPhoneBill -> R.drawable.ic_irancellcellphonebill
    Product.InquiryRightelCellPhoneBill -> R.drawable.ic_rightelcellphonebill
    Product.InquiryCarTrafficFinesBill -> R.drawable.ic_trafficfinesbill
    Product.InquiryMotorcycleTrafficFinesBill -> R.drawable.ic_motor_product
    Product.InquiryTrafficPlanTollBill -> R.drawable.ic_trafficplantollbill
    Product.InquiryFreewayTollBill -> R.drawable.ic_freewaytollbill
    Product.InquiryDrivingNegativePoint -> R.drawable.ic_drivingnegativepoint
    Product.InquiryDrivingAccidents -> R.drawable.ic_drivingaccidents
    Product.InquiryThirdPartyInsurance -> R.drawable.ic_thirdpartyinsurance
    Product.InquiryRevokedPlateNumber -> R.drawable.ic_revokedplatenumber
    Product.InquiryTechnicalExaminationCertificate -> R.drawable.ic_technicalexaminationcertificate
    Product.InquiryCarFuelCardPostPackageStatus -> R.drawable.ic_carfuelcardpostpackagestatus
    Product.InquiryCarFuelCardPostPackageNumber -> R.drawable.ic_carfuelcardpostpackagestatus
    Product.InquiryCarIdentificationDocumentsPostPackageStatus -> R.drawable.ic_caridentificationdocumentspostpackagestatus
    Product.InquiryCarIdentificationCardPostPackageStatus -> R.drawable.ic_caridentificationcardpostpackagestatus
    Product.InquiryMotorcycleIdentificationDocumentsPostPackageStatus -> R.drawable.ic_motorcycleidentificationdocumentspostpackagestatus
    Product.InquiryDrivingLicencePostPackageStatus -> R.drawable.ic_drivinglicencepostpackagestatus
    Product.InquiryMilitaryServiceCardPostPackageStatus -> R.drawable.ic_servicecardpostpackagestatus
    Product.InquiryAdministrativeJusticePostPackageStatus -> R.drawable.ic_administrativejusticepostpackagestatus
    Product.InquiryJusticeShares -> R.drawable.ic_justiceshares
    Product.InquirySocialSecurityInsuranceHistory -> R.drawable.ic_socialsecurityinsurancehistory
    Product.InquiryAirPolutionControl -> R.drawable.ic_airpolutioncontrol
    Product.InquiryTehranMunicipalityBuildingTollBill -> R.drawable.ic_shahrdari
    Product.InquiryMunicipalityPropertyTollBill -> R.drawable.ic_shahrdari
    Product.InquiryTopUpInternetPackage -> R.drawable.ic_net
    Product.InquiryTopUpCharge -> R.drawable.ic_simcard
    Product.InquiryJusticeSharesValue -> R.drawable.ic_logo_saham
    Product.InquirySocialSecurityPensionReceipt -> R.drawable.ic_pension
    Product.InquirySocialSecurityPension -> R.drawable.ic_pension_real
    Product.InquiryGovernmentRetirement -> R.drawable.ic_farhangian
    Product.Exchange -> R.drawable.ic_daller
    Product.Coin -> R.drawable.ic_coin
    Product.Gold -> R.drawable.ic_gold
    Product.InquiryExchangeMarketDataForeignCurrency -> R.drawable.ic_daller
    Product.InquiryExchangeMarketDataCryptoCurrency -> R.drawable.ic_cryptocurrency
    Product.InquiryExchangeMarketDataGold -> R.drawable.ic_gold
//    Product.ChooseServices -> R.drawable.ic_add_circle_outline_black_24dp
    Product.InquirySubventionPaymentHistory -> R.drawable.ic_yarane
    Product.USSDCommon -> R.drawable.ic_ussd_social
    Product.USSDOperator -> R.drawable.ic_ussd_operator
    Product.InquiryPostPackageTracking -> R.drawable.ic_post
    Product.CryptoCurrency -> R.drawable.ic_cryptocurrency
    Product.Sheba -> R.drawable.ic_sheba
    Product.InquirySocialSecurityInsuranceMedicalCredit -> R.drawable.ic_daftarche
    Product.SocialSecurityUnemploymentInsurance -> R.drawable.ic_bime_bikari
    Product.InquiryEBankSayadCheque -> R.drawable.ic_cheque
    Product.InquiryMunicipalityTaxiFaresBill -> R.drawable.ic_taxi
    Product.InquiryMunicipalityCarAnnualTollBill -> R.drawable.ic_car_annual_toll
    Product.InquiryMunicipalityCarTollBill -> R.drawable.ic_municipality_car_toll
    Product.InquirySocialSecurityHealthInsurance -> R.drawable.ic_health_insurance
    Product.InquiryGovernmentRetirementReceipt -> R.drawable.ic_retirement_receipt

    Product.MOBILE -> R.drawable.ic_mobile
    Product.RETIREMENT -> R.drawable.ic_retirement
    Product.PENSION_RECEIPT -> R.drawable.ic_receipt
    Product.INSURANCE -> R.drawable.ic_insurance_notebook

    else -> R.drawable.ic_search_black_24dp
}
