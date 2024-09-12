mvn clean compile assembly:single
CLIENT_ID="sp-cicd-azure-personal-25c7ae74-8eec-4ee8-935c-8921a79fff40-azure-demo" \
  CLIENT_SECRET='xxx' \
  AZURE_TENANT_ID='82a60e0b-b00c-45e3-b4cb-74ee4799e0c1' \
  AZURE_SUBSCRIPTION_ID='91b7f870-d0e7-4a8f-8243-fdf90c380200' \
  AZURE_SP_CLIENT_ID='28760334-d95d-4d8d-9ed2-8c488081f901' \
  AZURE_SP_CLIENT_SECRET='xxx' \
  FRACTAL_ENVIRONMENT_OWNER_ID="25c7ae74-8eec-4ee8-935c-8921a79fff40" \
  FRACTAL_ENVIRONMENT_SHORT_NAME="fractal-scai-test-v1" \
  FRACTAL_CLOUD_RESOURCE_GROUP_ID="d78ce1f5-da28-49d5-b56b-f40dab1711c5" \
  FRACTAL_ENVIRONMENT_NAME="Fractal SCAI Test" \
  java -jar target/pocs.migration.architecture-jar-with-dependencies.jar