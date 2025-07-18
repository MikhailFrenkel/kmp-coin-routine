package dev.coinroutine.app.trade.presentation.sell

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.repeatOnLifecycle
import dev.coinroutine.app.trade.presentation.common.TradeScreen
import dev.coinroutine.app.trade.presentation.common.TradeType
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun SellScreen(
    coinId: String,
    navigateToPortfolio: () -> Unit,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val viewModel = koinViewModel<SellViewModel>(
        parameters = {
            parametersOf(coinId)
        }
    )
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel.events) {
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            viewModel.events.collect{ event ->
                when (event) {
                    SellEvents.SellSuccess -> {
                        navigateToPortfolio()
                    }
                }
            }
        }
    }

    TradeScreen(
        state = state,
        tradeType = TradeType.SELL,
        onAmountChange = viewModel::onAmountChanged,
        onSubmitClicked = viewModel::onSellClicked
    )
}