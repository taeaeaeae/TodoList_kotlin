package com.teamsparta.courseregistation.domain.user.exception

data class InvalidCredentialException (
    override val message: String? = "The Credential Exception"
): RuntimeException()