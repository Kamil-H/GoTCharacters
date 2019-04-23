package com.kamilh.gotcharacters.interactors

interface Interactor<in I, out O> {
    suspend operator fun invoke(executeParams: I): O
}
