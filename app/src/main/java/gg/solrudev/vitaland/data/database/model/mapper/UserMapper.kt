package gg.solrudev.vitaland.data.database.model.mapper

import gg.solrudev.vitaland.data.database.model.UserModel
import gg.solrudev.vitaland.data.mapper.Mapper
import gg.solrudev.vitaland.domain.model.PersonName
import gg.solrudev.vitaland.domain.model.User
import javax.inject.Inject

interface UserMapper : Mapper<UserModel, User>
interface UserModelMapper : Mapper<User, UserModel>

class UserMapperImpl @Inject constructor() : UserMapper {

	override fun invoke(userModel: UserModel): User {
		val personName = PersonName(userModel.firstName, userModel.lastName, userModel.patronymic)
		return User(userModel.id, personName, userModel.email, userModel.phone, userModel.role)
	}
}

class UserModelMapperImpl @Inject constructor() : UserModelMapper {

	override fun invoke(user: User) = UserModel(
		user.id,
		user.name.firstName,
		user.name.lastName,
		user.name.patronymic,
		user.email,
		user.phone,
		user.role
	)
}