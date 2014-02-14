<?php

class Pages extends CI_Controller{
	public function __construct(){
		parent::__construct();
		$this->load->model('candidates');
	}
	public function view ($page = 'home'){
		$candidates = $this->candidates->get_candidates();
		$data = array('candidates' => $candidates);
		print($data);
		$this->load->view('pages/candidates', $data);
	}
}
