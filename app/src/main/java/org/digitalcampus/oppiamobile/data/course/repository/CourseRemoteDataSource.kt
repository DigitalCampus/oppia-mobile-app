package org.digitalcampus.oppiamobile.data.course.repository

import org.digitalcampus.oppiamobile.data.course.remote.CourseRemoteService
import org.digitalcampus.oppiamobile.data.user.remote.auth.AuthRemoteService
import javax.inject.Inject

class CourseRemoteDataSource @Inject constructor(private val courseRemoteService: CourseRemoteService) {
}