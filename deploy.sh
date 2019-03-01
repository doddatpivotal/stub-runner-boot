# Edit to match your Bintray URL and M2 repo ID setting (check your ~/.m2/settings.xml file)
MAVEN_REPO_URL=https://api.bintray.com/maven/dpfeffer/maven-repo/cloudfoundry-stub-runner-boot
MAVEN_REPO_ID=bintray

# Build and upload
./mvnw clean deploy -Ddistribution.management.release.url="${MAVEN_REPO_URL}" -Ddistribution.management.release.id="${MAVEN_REPO_ID}"
