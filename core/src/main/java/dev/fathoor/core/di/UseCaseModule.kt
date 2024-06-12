package dev.fathoor.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.fathoor.core.domain.repository.user.LocalUserRepository
import dev.fathoor.core.domain.repository.user.SessionRepository
import dev.fathoor.core.domain.usecase.auth.LoginUseCase
import dev.fathoor.core.domain.usecase.auth.RegisterUseCase
import dev.fathoor.core.domain.usecase.user.DeleteLocalUserUseCase
import dev.fathoor.core.domain.usecase.user.DeleteSessionUseCase
import dev.fathoor.core.domain.usecase.user.GetLocalUserUseCase
import dev.fathoor.core.domain.usecase.user.GetSessionUseCase
import dev.fathoor.core.domain.usecase.user.InsertLocalUserUseCase
import dev.fathoor.core.domain.usecase.user.InsertSessionUseCase
import dev.fathoor.core.domain.usecase.user.UpdateLocalUserUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetLocalUserUseCase(localUserRepository: LocalUserRepository): GetLocalUserUseCase {
        return GetLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideInsertLocalUserUseCase(localUserRepository: LocalUserRepository): InsertLocalUserUseCase {
        return InsertLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideUpdateLocalUserUseCase(localUserRepository: LocalUserRepository): UpdateLocalUserUseCase {
        return UpdateLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideDeleteLocalUserUseCase(localUserRepository: LocalUserRepository): DeleteLocalUserUseCase {
        return DeleteLocalUserUseCase(localUserRepository)
    }

    @Provides
    fun provideGetSessionUseCase(sessionRepository: SessionRepository): GetSessionUseCase {
        return GetSessionUseCase(sessionRepository)
    }

    @Provides
    fun provideInsertSessionUseCase(sessionRepository: SessionRepository): InsertSessionUseCase {
        return InsertSessionUseCase(sessionRepository)
    }

    @Provides
    fun provideDeleteSessionUseCase(sessionRepository: SessionRepository): DeleteSessionUseCase {
        return DeleteSessionUseCase(sessionRepository)
    }

    @Provides
    fun provideLoginUseCase(localUserRepository: LocalUserRepository): LoginUseCase {
        return LoginUseCase(localUserRepository)
    }

    @Provides
    fun provideRegisterUseCase(localUserRepository: LocalUserRepository): RegisterUseCase {
        return RegisterUseCase(localUserRepository)
    }
}
