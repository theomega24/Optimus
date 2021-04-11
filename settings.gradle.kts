rootProject.name = "Optimus"

setupSubproject("optimus-api") {
    projectDir = file("api")
}

include("plugin")

inline fun setupSubproject(name: String, block: ProjectDescriptor.() -> Unit) {
    include(name)
    project(":$name").apply(block)
}
