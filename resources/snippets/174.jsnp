@Override
	protected void prepareBootstrapRegistryBuilder(BootstrapServiceRegistryBuilder builder) {
		super.prepareBootstrapRegistryBuilder( builder );
		builder.with(
				new Integrator() {

				    @Override
					public void integrate(
							Configuration configuration,
							SessionFactoryImplementor sessionFactory,
							SessionFactoryServiceRegistry serviceRegistry) {
                        integrate(serviceRegistry);
					}

                    @Override
				    public void integrate( MetadataImplementor metadata,
				                           SessionFactoryImplementor sessionFactory,
				                           SessionFactoryServiceRegistry serviceRegistry ) {
                        integrate(serviceRegistry);
				    }

                    private void integrate( SessionFactoryServiceRegistry serviceRegistry ) {
                        serviceRegistry.getService( EventListenerRegistry.class ).prependListeners(EventType.LOAD,
                                                                                                   new CustomLoadListener());
                    }

					@Override
					public void disintegrate(
							SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
					}
				}
		);
	}