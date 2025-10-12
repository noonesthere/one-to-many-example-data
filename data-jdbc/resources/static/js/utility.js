(function () {
  document.addEventListener('htmx:afterSwap', ev => {
    const dialog = ev.detail.elt.querySelector('#person-form-dialog');
//    debugger;
    if (dialog) {
      dialog.showModal();

      const closeBtn = dialog.querySelector('#close-dialog-button');
      if (closeBtn) {
        closeBtn.addEventListener('click', () => dialog.close());
      }
    }
  });
})();


  function exportWithFilters() {

    const inputs = document.querySelectorAll('#filters input[name]');
    const params = Array.from(inputs)
      .map(input => {
        return input.name + '=' + encodeURIComponent(input.value);
      })
      .join('&');
    window.open('/export?' + params, '_blank');
  }
