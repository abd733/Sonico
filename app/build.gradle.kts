plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.google.gms.google.services)
}

android {
    namespace = "com.example.sonicochat"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sonicochat"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures{
        dataBinding = true
        viewBinding = true
    }
}
dependencies {

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("de.hdodenhof:circleimageview:3.1.0")
    implementation("com.squareup.picasso:picasso:2.71828")
    implementation(libs.firebase.storage)
    implementation(libs.firebase.analytics)
    implementation(libs.firebase.database)
    implementation(libs.activity)
    implementation(libs.firebase.firestore)

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation("com.facebook.android:facebook-login:12.0.0")
    implementation("org.parceler:parceler-api:1.1.13")
    annotationProcessor("org.parceler:parceler:1.1.13")


    implementation("com.github.bumptech.glide:glide:4.15.1")
    implementation("com.google.android.gms:play-services-auth:21.1.1")

    implementation("com.google.firebase:firebase-auth:21.0.8")
    implementation("com.google.android.gms:play-services-auth:20.4.0")
    implementation("com.facebook.android:facebook-login:12.0.0")
    implementation("org.parceler:parceler-api:1.1.13")
    annotationProcessor("org.parceler:parceler:1.1.13")
    implementation("com.firebaseui:firebase-ui-database:8.0.0")


    implementation("com.hbb20:ccp:2.5.0")

    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-auth")

    implementation("com.google.firebase:firebase-firestore:24.4.2")
    implementation("com.firebaseui:firebase-ui-firestore:8.0.0")
}
