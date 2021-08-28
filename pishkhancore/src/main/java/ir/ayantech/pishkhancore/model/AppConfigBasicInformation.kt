package ir.ayantech.pishkhancore.model

data class AppConfigBasicInformationOutput(
    val AboutUs: List<AboutU>,
    val ContactUs: List<ContactU>,
    val FrequentlyAskedQuestionsCategories: List<FrequentlyAskedQuestionsCategory>,
    val Support: List<Support>,
    val TermsAndConditions: List<TermsAndCondition>
)

data class AboutU(
    val Key: String,
    val Value: String
)

data class ContactU(
    val Key: String,
    val Value: String
)

data class FrequentlyAskedQuestionsCategory(
    val Services: List<Service>,
    val Type: TypeX
)

data class Support(
    val Key: String,
    val Value: String
)

data class TermsAndCondition(
    val Key: String,
    val Value: String
)

data class Service(
    val QuestionList: List<Question>,
    val Type: Type
)

data class TypeX(
    val Name: String,
    val ShowName: String
)

data class Question(
    val Key: String,
    val Value: String
)