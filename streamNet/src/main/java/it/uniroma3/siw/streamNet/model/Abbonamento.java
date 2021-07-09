package it.uniroma3.siw.streamNet.model;

public enum Abbonamento {
	SILVER {
		@Override
		public Abbonamento upgrade() {
			return GOLD;
		}
	},GOLD {
		@Override
		public Abbonamento upgrade() {
			return PLATINUM;
		}
	},PLATINUM {
		@Override
		public Abbonamento upgrade() {
			return PLATINUM;
		}
	};
	
	public abstract Abbonamento upgrade();
}
