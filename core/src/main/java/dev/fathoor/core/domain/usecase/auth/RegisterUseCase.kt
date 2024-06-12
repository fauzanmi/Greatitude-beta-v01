package dev.fathoor.core.domain.usecase.auth

import dev.fathoor.core.data.local.entity.user.LocalUserEntity
import dev.fathoor.core.domain.model.auth.UserRegister
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.usecase.BaseSuspendUseCase
import dev.fathoor.core.util.convertStringDateToMillis
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val localUserRepository: LocalUserRepository
) : BaseSuspendUseCase<UserRegister, Flow<LocalUserEntity>> {
    override suspend fun execute(params: UserRegister): Flow<LocalUserEntity> {
        val user = LocalUserEntity(
            email = params.email,
            password = params.password,
            name = params.name,
            dob = convertStringDateToMillis(params.dob)
        )

        localUserRepository.insert(user)

        return flowOf(localUserRepository.getLocalUserByEmail(user.email)!!)
    }
}
