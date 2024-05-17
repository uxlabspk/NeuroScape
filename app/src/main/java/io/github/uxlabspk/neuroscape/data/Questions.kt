package io.github.uxlabspk.neuroscape.data

data class Questions(val id: Int, val text: String, val options: List<String> = listOf("Always", "Usually", "Sometimes", "Rarely", "Never"))