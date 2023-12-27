package com.raerossi.notekeeper.ui.features.registration

data class SignUpUser(
    var name: String = "",
    var email: String = "",
    var password: String = "",
    var confirmedPassword: String = ""
)