package lotto.domain

import lotto.data.LottoNums
import lotto.data.LottoResult
import lotto.ui.LottoCustomerScreen
import lotto.ui.LottoManagerScreen

class LottoManager {

    private var lottoCounts = 0
    private var lottoAmount : Long = 0
    private var lottoNumsList = emptyList<List<Int>>()
    private lateinit var lottoNums : LottoNums
    private lateinit var lottoResult : LottoResult
    private var revenue : Double = 0.0

    fun startGame(){

        val customer = Customer()

        lottoAmount = customer.getPurchaseAmounts()
        lottoCounts = customer.getPurchaseCounts()
        lottoNumsList = customer.lottoNumsList

        val lottoCustomerScreen = LottoCustomerScreen(lottoCounts,lottoNumsList)
        lottoCustomerScreen.printLottoNumsList()

        val inputManager = LottoInputManager()
        lottoNums = inputManager.getLottoNums()

        val outputManager = LottoOutputManager(lottoNumsList,lottoNums)
        outputManager.calculateRevenue(lottoAmount)
        lottoResult = outputManager.getResult()
        revenue = outputManager.getRevenue()

        val lottoManagerScreen = LottoManagerScreen(lottoResult,revenue)
        lottoManagerScreen.printLottoResult()

    }







}