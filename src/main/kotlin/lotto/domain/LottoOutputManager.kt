package lotto.domain

import lotto.data.ConstNum
import lotto.data.LottoNums
import lotto.data.LottoResult
import lotto.data.WinningCount


class LottoOutputManager() {


    private val _lottoResult : LottoResult = LottoResult()
    private   var _revenue  : Double = 0.0


    fun getResult() : LottoResult{
        return _lottoResult
    }
    fun getRevenue() :Double{
        return _revenue
    }

    fun calculateRevenue(purchaseAmounts : Long) {

        val amounts  = (ConstNum.Three_Lotto_Amount * _lottoResult.three + ConstNum.Four_Lotto_Amount * _lottoResult.four
        + ConstNum.Five_Lotto_Amount * _lottoResult.five + ConstNum.Five_Bonus_Lotto_Amount* _lottoResult.bonus
        + ConstNum.Six_Lotto_Amount * _lottoResult.six).toBigInteger()

        _revenue = (amounts.toDouble() / purchaseAmounts.toDouble()) * 100
    }

    fun calculateResult(inputNums : List<List<Int>> , lottoNums: LottoNums) {

        inputNums.forEach {
            val resultCount = Lotto(it).calculateLotto(lottoNums)
            when(resultCount.listCounts){
                WinningCount.THREE.count -> _lottoResult.three +=1

                WinningCount.FOUR.count -> {
                    _lottoResult.four +=1
                }

                WinningCount.FIVE.count-> {
                    if(!resultCount.bonus)_lottoResult.five+=1
                    else _lottoResult.bonus +=1
                }

                WinningCount.SIX.count ->{
                    _lottoResult.six+=1
                }
            }


        }
    }


}