function deleteSymptoms(symptomsId) {
  fetch("/delete-symptoms", {
    method: "POST",
    body: JSON.stringify({ symptomsId: symptomsId }),
  }).then((_res) => {
    window.location.href = "/";
  });
}