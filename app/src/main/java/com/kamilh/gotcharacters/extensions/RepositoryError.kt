package com.kamilh.gotcharacters.extensions

import com.kamilh.gotcharacters.R
import com.kamilh.gotcharacters.data.Alert
import com.kamilh.gotcharacters.repository.RepositoryError
import com.kamilh.gotcharacters.util.ResourceProvider

fun RepositoryError.toAlert(resourceProvider: ResourceProvider, okAction: (() -> Unit)? = null) = Alert(
    title = resourceProvider.getString(R.string.ErrorTitle),
    message = message(resourceProvider)
).addOkAction(okAction)
