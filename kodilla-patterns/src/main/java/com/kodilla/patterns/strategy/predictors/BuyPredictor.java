package com.kodilla.patterns.strategy.predictors;

public sealed interface BuyPredictor permits ConservativePredictor, BalancedPredictor, AggressivePredictor {
    String predictWhatToBuy();
}
