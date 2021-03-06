package ir.ayantech.pishkhancore.model

import ir.ayantech.pishkhancore.helper.removePishkhanFromString

data class PaymentHistoryGetTransactionListInput(
    val TransactionCategoryTypeName: String,
    val TransactionTypeName: String
)

data class PaymentHistoryGetTransactionListOutput(
    val TotalAmountOfTransactions: Long,
    val TotalNumberOfTransactions: Long,
    val Transactions: List<Transaction>?
)

data class Transaction(
    val Amount: Long,
    val DateTime: DateTime,
    val SearchKeywords: String,
    val Status: NameShowName,
    val Icon: NameShowName?,
    val Title: String,
    val Type: Type,
    val UniqueID: String
)

data class Type(
    val Category: NameShowName,
    val Name: String,
    val ShowName: String
)

open class NameShowName(
    open val Name: String,
    private val ShowName: String
) {
    companion object {
        const val ValidForPayment = "ValidForPayment"
    }

    val validForPayment: Boolean
        get() = this.Name == ValidForPayment

    val getUsableShowName: String
    get() = this.ShowName.removePishkhanFromString()
}

data class DateTime(
    val Gregorian: MonshiPlusDateTime,
    val Lunar: MonshiPlusDateTime,
    val Persian: MonshiPlusDateTime,
    val Time: String
) {
    override fun toString() = "$Persian $Time"

    fun toFullDate() = "${Persian.DayString} ${Persian.Day} ${Persian.MonthString} ${Persian.Year}"
}

data class MonshiPlusDateTime(
    val Day: Int,
    val DayString: String,
    val DateFormatted: String,
    val Month: Int,
    val MonthString: String,
    val Year: Int
) {
    override fun toString() = DateFormatted
}