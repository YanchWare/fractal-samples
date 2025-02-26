mvn clean compile assembly:single
CI_CD_SERVICE_ACCOUNT_NAME="sp-cicd-azure-personal-xxx-azure-demo" \
  CI_CD_SERVICE_ACCOUNT_SECRET='xxx' \
  AZURE_TENANT_ID='xxx' \
  AZURE_SUBSCRIPTION_ID='xxx' \
  AZURE_SP_CLIENT_ID='xxx' \
  AZURE_SP_CLIENT_SECRET='xxx' \
  DEFAULT_CI_CD_PROFILE_DESCRIPTION='xxx' \
  DEFAULT_CI_CD_PROFILE_DISPLAY_NAME='xxx' \
  DEFAULT_CI_CD_PROFILE_SHORT_NAME='ci-cd-profile-001' \
  DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_DATA='xxx' \
  DEFAULT_CI_CD_PROFILE_SSH_PRIVATE_KEY_PASSPHRASE='xxx' \
  FRACTAL_ENVIRONMENT_OWNER_ID="xxx" \
  FRACTAL_ENVIRONMENT_SHORT_NAME="fractal-test-v1" \
  FRACTAL_CLOUD_RESOURCE_GROUP_ID="xxx" \
  FRACTAL_ENVIRONMENT_NAME="Fractal Test" \
  java -jar target/pocs.migration.architecture-jar-with-dependencies.jar