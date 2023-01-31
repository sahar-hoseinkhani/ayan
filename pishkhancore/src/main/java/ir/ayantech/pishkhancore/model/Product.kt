package ir.ayantech.pishkhancore.model

import android.view.View
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
    const val InquiryMunicipalityVehicleParkTollBill = "InquiryMunicipalityVehicleParkTollBill"

    const val InquiryExchangeMarketDataForeignCurrency = "InquiryExchangeMarketDataForeignCurrency"
    const val InquiryExchangeMarketDataCryptoCurrency = "InquiryExchangeMarketDataCryptoCurrency"
    const val InquiryExchangeMarketDataGold = "InquiryExchangeMarketDataGold"
    const val SocialSecurityUnemploymentInsurance = "SocialSecurityUnemploymentInsuranceCalculator"
    const val InquiryEBankSayadCheque = "InquirySayadCheque"
    const val InquiryCarFuelCardPostPackageNumber = "InquiryCarFuelCardPostPackageNumber"
    const val InquirySocialSecurityHealthInsurance = "InquirySocialSecurityHealthInsurance"
    const val InquiryGovernmentRetirementReceipt = "InquiryGovernmentRetirementReceipt"
    const val InquiryNajaTrafficFinesCarSummary = "InquiryNajaTrafficFinesCarSummary"
    const val InquiryNajaTrafficFinesCar = "InquiryNajaTrafficFinesCar"
    const val InquiryNajaTrafficFinesMotorcycle = "InquiryNajaTrafficFinesMotorcycle"
    const val InquiryNajaPlateNumbers = "InquiryNajaPlateNumbers"
    const val InquiryNajaDrivingLicenceStatus = "InquiryNajaDrivingLicenceStatus"
    const val InquiryNajaDrivingLicenceNegativePoint = "InquiryNajaDrivingLicenceNegativePoint"
    const val InquiryNajaCarIdentificationDocumentsStatus =
        "InquiryNajaCarIdentificationDocumentsStatus"
    const val InquiryNajaPassportStatus = "InquiryNajaPassportStatus"
    const val InquiryNajaExitBanStatus = "InquiryNajaExitBanStatus"
    const val InquiryNajaTrafficFinesMotorcycleSummary = "InquiryNajaTrafficFinesMotorcycleSummary"

    const val SubscriptionPlan = "SubscriptionPlan"
    const val NativeAd = "NativeAd"

    const val PENSION_RECEIPT = "pension_receipt"
    const val RETIREMENT = "retirement"
    const val INSURANCE = "insurance"
    const val MOBILE = "mobile"
    const val TRAFFIC_FINES = "traffic_fines"
    const val MOTOR_TRAFFIC_FINES = "motor_traffic_fines"
}

fun String.getProductIcon() = when (this) {
    Product.CloudStorageUploadFiles -> R.drawable.ic_search_black_24dp
    Product.CloudStorageDownloadFiles -> R.drawable.ic_search_black_24dp
    Product.InquiryWaterBill -> R.drawable.ic_waterbill
    Product.InquiryElectricBill -> R.drawable.ic_electricbill
    Product.InquiryGasBill -> R.drawable.ic_gasbill
    Product.InquiryLandlinePhoneBill -> R.drawable.ic_landlinephonebill
    Product.InquiryMciCellPhoneBill -> R.drawable.ic_mcicellphonebill
    Product.InquiryIrancellCellPhoneBill -> R.drawable.ic_irancellcellphonebill
    Product.InquiryRightelCellPhoneBill -> R.drawable.ic_rightelcellphonebill
    Product.InquiryCarTrafficFinesBill -> R.drawable.ic_traffic_fines_by_barcode
    Product.InquiryMotorcycleTrafficFinesBill -> R.drawable.ic_motor_with_barcode
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
    Product.ChooseServices -> R.drawable.ic_add_circle_outline_black_24dp
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
    Product.InquiryMunicipalityCarTollBill -> R.drawable.ic_traffic_plan
    Product.InquirySocialSecurityHealthInsurance -> R.drawable.ic_health_insurance
    Product.InquiryGovernmentRetirementReceipt -> R.drawable.ic_retirement_receipt
    Product.InquiryMunicipalityVehicleParkTollBill -> R.drawable.ic_park_toll
    Product.InquiryNajaTrafficFinesCarSummary -> R.drawable.ic_traffic_fines_no_detail
    Product.InquiryNajaTrafficFinesCar -> R.drawable.ic_traffc_fines_by_plate
    Product.InquiryNajaTrafficFinesMotorcycle -> R.drawable.ic_motor_by_plate
    Product.InquiryNajaTrafficFinesMotorcycleSummary -> R.drawable.ic_motor_traffic_fines_no_detail
    Product.InquiryNajaPlateNumbers -> R.drawable.ic_active_plates
    Product.InquiryNajaDrivingLicenceStatus -> R.drawable.ic_lisence_status
    Product.InquiryNajaDrivingLicenceNegativePoint -> R.drawable.ic_negative_points
    Product.InquiryNajaCarIdentificationDocumentsStatus -> R.drawable.ic_naji_card
    Product.InquiryNajaPassportStatus -> R.drawable.ic_passport
    Product.InquiryNajaExitBanStatus -> R.drawable.ic_exit_ban

    Product.MOBILE -> R.drawable.ic_mobile
    Product.RETIREMENT -> R.drawable.ic_retirement
    Product.PENSION_RECEIPT -> R.drawable.ic_receipt
    Product.INSURANCE -> R.drawable.ic_insurance_notebook
    Product.MOTOR_TRAFFIC_FINES -> R.drawable.ic_motor_product
    Product.TRAFFIC_FINES -> R.drawable.ic_trafficfinesbill

    "SubscriptionPlan" -> R.drawable.ic_coin_premiuim
    "select" -> R.drawable.ic_outline_add_box_24
    else -> R.drawable.ic_search_black_24dp
}

fun String.postInquiryAbility() = when (this) {

    Product.InquiryNajaPassportStatus -> View.VISIBLE
//    Product.InquiryNajaDrivingLicenceStatus -> View.VISIBLE
//    Product.InquiryNajaCarIdentificationDocumentsStatus -> View.VISIBLE
    else -> View.GONE
}


