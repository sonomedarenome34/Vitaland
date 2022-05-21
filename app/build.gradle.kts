plugins {
	id("com.android.application")
	kotlin("android")
	kotlin("kapt")
	id("dagger.hilt.android.plugin")
	id("androidx.navigation.safeargs.kotlin")
	id("com.google.devtools.ksp") version "1.6.21-1.0.5"
}

val androidGradleVersion: String by rootProject.extra
val hiltVersion: String by rootProject.extra
val navigationVersion: String by rootProject.extra

android {
	compileSdk = 32

	defaultConfig {
		applicationId = "gg.solrudev.vitaland"
		minSdk = 21
		targetSdk = 32
		versionCode = 1
		versionName = "1.0"

		testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
	}

	buildTypes {
		named("release") {
			isMinifyEnabled = false
			setProguardFiles(
				listOf(
					getDefaultProguardFile("proguard-android-optimize.txt"),
					"proguard-rules.pro"
				)
			)
		}
	}
	compileOptions {
		sourceCompatibility = JavaVersion.VERSION_1_8
		targetCompatibility = JavaVersion.VERSION_1_8
	}
	kotlinOptions {
		jvmTarget = "1.8"
	}
	buildFeatures {
		viewBinding = true
	}
}

dependencies {

	val retrofitVersion = "2.9.0"
	val moshiVersion = "1.13.0"
	val roomVersion = "2.4.2"

	kapt("com.google.dagger:hilt-compiler:$hiltVersion")
	kapt("androidx.hilt:hilt-compiler:1.0.0")
	ksp("com.squareup.moshi:moshi-kotlin-codegen:$moshiVersion")
	ksp("androidx.room:room-compiler:$roomVersion")

	implementation("com.google.dagger:hilt-android:$hiltVersion")
	implementation("androidx.activity:activity-ktx:1.4.0")
	implementation("androidx.room:room-ktx:$roomVersion")

	implementation("com.squareup.moshi:moshi:$moshiVersion")
	implementation("com.squareup.retrofit2:retrofit:$retrofitVersion")
	implementation("com.squareup.retrofit2:converter-moshi:$retrofitVersion")

	implementation("androidx.appcompat:appcompat:1.4.1")
	implementation("com.google.android.material:material:1.6.0")
	implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1")
	implementation("androidx.navigation:navigation-fragment-ktx:$navigationVersion")
	implementation("androidx.navigation:navigation-ui-ktx:$navigationVersion")
	implementation("com.github.kirich1409:viewbindingpropertydelegate-noreflection:1.5.6")
	testImplementation("junit:junit:4.13.2")
	androidTestImplementation("androidx.test.ext:junit:1.1.3")
	androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}