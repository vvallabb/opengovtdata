<?php

class Pages extends CI_Controller{
	public function __construct(){
		parent::__construct();
		$this->load->model('candidates');
	}
	public function view ($page = 'home'){
		$candidates = $this->candidates->get_candidates();
		$data = array('candidates' => $candidates);
		$this->load->view('pages/candidates', $data);
	}
	
	public function persistmps(){
		$candidates = $this->candidates->persist_candidates();
	}
}
